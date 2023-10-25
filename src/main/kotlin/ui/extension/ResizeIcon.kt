package ui.extension

import javax.swing.ImageIcon

fun ImageIcon.resizeIcon(width: Int, height: Int): ImageIcon {
    val image = this.image
    val newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)
    return ImageIcon(newImage)
}