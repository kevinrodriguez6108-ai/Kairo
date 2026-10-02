package com.example.ui.components

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import kotlin.math.min

object ProfilePhotoHelper {
    private const val PHOTO_DIR = "profile_photos"
    private const val MAX_AVATAR_DIMENSION = 480

    fun saveUriToInternalStorage(context: Context, uri: Uri): String {
        return try {
            val bitmap = context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
            if (bitmap != null) {
                saveBitmapToInternalStorage(context, bitmap)
            } else {
                uri.toString()
            }
        } catch (_: Exception) {
            uri.toString()
        }
    }

    fun saveBitmapToInternalStorage(context: Context, bitmap: Bitmap): String {
        return try {
            val dir = File(context.filesDir, PHOTO_DIR)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val scaled = scaleCenterSquare(bitmap, MAX_AVATAR_DIMENSION)
            val destFile = File(dir, "avatar_${System.currentTimeMillis()}.jpg")
            FileOutputStream(destFile).use { out ->
                scaled.compress(Bitmap.CompressFormat.JPEG, 88, out)
            }
            destFile.absolutePath
        } catch (_: Exception) {
            "camera_photo_${System.currentTimeMillis()}"
        }
    }

    fun loadImageBitmap(context: Context, photoUri: String?): ImageBitmap? {
        if (photoUri.isNullOrBlank()) return null
        return try {
            val file = File(photoUri)
            val bitmap: Bitmap? = when {
                file.exists() -> BitmapFactory.decodeFile(file.absolutePath)
                photoUri.startsWith("content://") || photoUri.startsWith("file://") -> {
                    context.contentResolver.openInputStream(Uri.parse(photoUri))?.use {
                        BitmapFactory.decodeStream(it)
                    }
                }
                else -> null
            }
            bitmap?.asImageBitmap()
        } catch (_: Exception) {
            null
        }
    }

    private fun scaleCenterSquare(source: Bitmap, maxDimension: Int): Bitmap {
        val size = min(source.width, source.height)
        val xOffset = (source.width - size) / 2
        val yOffset = (source.height - size) / 2
        val cropped = if (size > 0) {
            Bitmap.createBitmap(source, xOffset, yOffset, size, size)
        } else {
            source
        }
        return if (cropped.width > maxDimension) {
            Bitmap.createScaledBitmap(cropped, maxDimension, maxDimension, true)
        } else {
            cropped
        }
    }
}

@Composable
fun ProfileAvatar(
    photoUri: String?,
    displayName: String,
    size: Dp,
    modifier: Modifier = Modifier,
    fallbackText: String? = null,
    fontSize: TextUnit = 16.sp,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
    borderWidth: Dp = 2.dp
) {
    val context = LocalContext.current
    val imageBitmap = remember(photoUri) {
        ProfilePhotoHelper.loadImageBitmap(context, photoUri)
    }

    Surface(
        shape = CircleShape,
        color = containerColor,
        modifier = modifier
            .size(size)
            .border(borderWidth, borderColor, CircleShape)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Foto de perfil de ${displayName.ifBlank { "usuario" }}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } else {
                val initial = fallbackText ?: displayName.trim().firstOrNull()?.uppercaseChar()?.toString()
                if (!initial.isNullOrBlank()) {
                    Text(
                        text = initial,
                        fontSize = fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        color = contentColor
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Sin foto de perfil",
                        tint = contentColor,
                        modifier = Modifier.size(size * 0.52f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilePhotoSelectorControls(
    photoUri: String?,
    displayName: String,
    onPhotoSelected: (String?) -> Unit,
    galleryButtonTag: String,
    cameraButtonTag: String,
    removeButtonTag: String,
    avatarTag: String,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 84.dp,
    compactMode: Boolean = false
) {
    val context = LocalContext.current
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var isErrorStatus by remember { mutableStateOf(false) }

    // 1. Lanzador de Galería (Android Photo Picker sin permisos de almacenamiento)
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            val savedPath = ProfilePhotoHelper.saveUriToInternalStorage(context, uri)
            onPhotoSelected(savedPath)
            isErrorStatus = false
            statusMessage = "Foto de galería actualizada."
        }
    }

    // 2. Lanzador de Cámara para tomar foto
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            val savedPath = ProfilePhotoHelper.saveBitmapToInternalStorage(context, bitmap)
            onPhotoSelected(savedPath)
            isErrorStatus = false
            statusMessage = "Foto tomada con la cámara y guardada."
        }
    }

    // 3. Lanzador de Permiso de Cámara en tiempo de ejecución
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            try {
                cameraLauncher.launch(null)
            } catch (_: ActivityNotFoundException) {
                isErrorStatus = true
                statusMessage = "No se encontró una aplicación de cámara disponible en este dispositivo."
            }
        } else {
            isErrorStatus = true
            statusMessage = "Permiso de cámara denegado. Puedes activar el permiso o elegir una foto de tu galería."
        }
    }

    val launchCameraFlow = {
        val hasCameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (hasCameraPermission) {
            try {
                cameraLauncher.launch(null)
            } catch (_: ActivityNotFoundException) {
                isErrorStatus = true
                statusMessage = "No se encontró una aplicación de cámara disponible en este dispositivo."
            }
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    val launchGalleryFlow = {
        try {
            galleryLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        } catch (_: ActivityNotFoundException) {
            isErrorStatus = true
            statusMessage = "No se encontró una galería disponible en este dispositivo."
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (compactMode) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .clickable { launchGalleryFlow() }
                        .testTag(avatarTag)
                ) {
                    ProfileAvatar(
                        photoUri = photoUri,
                        displayName = displayName,
                        size = avatarSize,
                        fontSize = 22.sp,
                        borderWidth = 2.5.dp,
                        borderColor = MaterialTheme.colorScheme.primary
                    )
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (!photoUri.isNullOrBlank()) Icons.Default.CheckCircle else Icons.Default.AddAPhoto,
                                contentDescription = "Cambiar foto de perfil",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = launchGalleryFlow,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag(galleryButtonTag)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoLibrary,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Galería",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = launchCameraFlow,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag(cameraButtonTag)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Cámara",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (!photoUri.isNullOrBlank()) {
                        TextButton(
                            onClick = {
                                onPhotoSelected(null)
                                isErrorStatus = false
                                statusMessage = "Foto de perfil eliminada."
                            },
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier
                                .align(Alignment.Start)
                                .testTag(removeButtonTag)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DeleteOutline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Quitar foto",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .clickable { launchGalleryFlow() }
                    .testTag(avatarTag)
            ) {
                ProfileAvatar(
                    photoUri = photoUri,
                    displayName = displayName,
                    size = avatarSize,
                    fontSize = 28.sp,
                    borderWidth = 3.dp,
                    borderColor = MaterialTheme.colorScheme.primary
                )
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (!photoUri.isNullOrBlank()) Icons.Default.CheckCircle else Icons.Default.AddAPhoto,
                            contentDescription = "Cambiar foto de perfil",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (!photoUri.isNullOrBlank()) "Foto de perfil lista" else "Elige una foto de galería o tómate una foto",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (!photoUri.isNullOrBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = launchGalleryFlow,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag(galleryButtonTag)
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Foto de Galería",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = launchCameraFlow,
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag(cameraButtonTag)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tomar Foto",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (!photoUri.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                TextButton(
                    onClick = {
                        onPhotoSelected(null)
                        isErrorStatus = false
                        statusMessage = "Foto de perfil eliminada."
                    },
                    modifier = Modifier.testTag(removeButtonTag)
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Quitar foto de perfil",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = statusMessage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isErrorStatus) {
                    MaterialTheme.colorScheme.errorContainer
                } else {
                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = statusMessage.orEmpty(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isErrorStatus) {
                        MaterialTheme.colorScheme.onErrorContainer
                    } else {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}
