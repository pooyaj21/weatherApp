package ui.panel.mainPanel

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.*
import javax.swing.ImageIcon

class MainPanelController {
    fun generateQRCode(
        url: String,
        width: Int,
        height: Int,
        backgroundColor: Color,
        foregroundColor: Color
    ): ImageIcon {
        val hints: MutableMap<EncodeHintType, Any> = EnumMap(EncodeHintType::class.java)
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints)
        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        bufferedImage.createGraphics()
        val graphics: Graphics2D = bufferedImage.graphics as Graphics2D
        graphics.color = backgroundColor
        graphics.fillRect(0, 0, width, height)
        graphics.color = foregroundColor
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (bitMatrix.get(x, y)) {
                    graphics.fillRect(x, y, 1, 1)
                }
            }
        }
        return ImageIcon(bufferedImage)
    }
}