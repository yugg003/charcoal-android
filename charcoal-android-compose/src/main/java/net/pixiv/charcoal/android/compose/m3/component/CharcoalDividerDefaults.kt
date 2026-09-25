package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 Divider の既定値として提供する。
 * 生の [androidx.compose.material3.HorizontalDivider] / [androidx.compose.material3.VerticalDivider] に渡して使う。
 *
 * Charcoal の Divider は border トークンで描く。ColorScheme の outlineVariant にも border を割り当てているが、
 * M3 側の既定ロールに依存せず Charcoal の意図を明示するため、`color = CharcoalDividerDefaults.color` を渡して使う。
 */
object CharcoalDividerDefaults {
    /** Divider の既定色。 */
    val color: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.border

    /** Divider の既定の太さ。M3 の既定値（1dp）に委ねる。 */
    val thickness: Dp get() = DividerDefaults.Thickness
}

// region Preview

/**
 * CharcoalDividerDefaults の全設定（color / thickness）を HorizontalDivider / VerticalDivider に渡した状態。
 */
@Composable
private fun CharcoalDividerGallery() {
    CharcoalPreviewColumn {
        Text("HorizontalDivider")
        HorizontalDivider(color = CharcoalDividerDefaults.color, thickness = CharcoalDividerDefaults.thickness)
        Text("VerticalDivider")
        Row(modifier = Modifier.height(40.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Left")
            VerticalDivider(color = CharcoalDividerDefaults.color, thickness = CharcoalDividerDefaults.thickness)
            Text("Right")
        }
    }
}

@PreviewLightDark
@Composable
private fun CharcoalDividerPreview() {
    CharcoalM3Theme { CharcoalDividerGallery() }
}

// endregion
