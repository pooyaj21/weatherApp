package ui.util

import javax.swing.ImageIcon

fun resizeIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon {
    val image = icon.image
    val newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
    return ImageIcon(newImage)
}