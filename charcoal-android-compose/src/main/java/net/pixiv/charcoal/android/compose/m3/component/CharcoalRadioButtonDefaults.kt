package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.compose.extension.overlayDisabledAlpha
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewRow
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 RadioButton のデフォルト色として提供する。
 * unselectedColor に text3 を使うのは M3 デフォルトの onSurfaceVariant (=text2) から意図的に逸脱し、
 * 視覚的な重みを抑えるための Charcoal 固有値。生の [androidx.compose.material3.RadioButton] に渡して使う。
 */
object CharcoalRadioButtonDefaults {

    /**
     * Charcoal トークンを既定値にした [RadioButtonColors] を返す。各色は引数で差し替えられる。
     * disabled 色の既定値は対応する有効時の色から算出する。
     */
    @Composable
    fun colors(
        selectedColor: Color = CharcoalTheme.colorToken.brand,
        unselectedColor: Color = CharcoalTheme.colorToken.text3,
        disabledSelectedColor: Color = selectedColor.overlayDisabledAlpha(),
        disabledUnselectedColor: Color = unselectedColor.overlayDisabledAlpha(),
    ): RadioButtonColors = RadioButtonDefaults.colors(
        selectedColor = selectedColor,
        unselectedColor = unselectedColor,
        disabledSelectedColor = disabledSelectedColor,
        disabledUnselectedColor = disabledUnselectedColor,
    )
}

// region Preview

/**
 * CharcoalRadioButtonDefaults.colors() の全設定。
 * selected / unselected × enabled / disabled（selectedColor / unselectedColor / disabledSelectedColor / disabledUnselectedColor）。
 */
@Composable
private fun CharcoalRadioButtonGallery() {
    CharcoalPreviewColumn {
        Text("enabled: selected / unselected")
        CharcoalPreviewRow {
            RadioButton(selected = true, onClick = {}, colors = CharcoalRadioButtonDefaults.colors())
            RadioButton(selected = false, onClick = {}, colors = CharcoalRadioButtonDefaults.colors())
        }
        Text("disabled: selected / unselected")
        CharcoalPreviewRow {
            RadioButton(selected = true, onClick = {}, enabled = false, colors = CharcoalRadioButtonDefaults.colors())
            RadioButton(selected = false, onClick = {}, enabled = false, colors = CharcoalRadioButtonDefaults.colors())
        }
    }
}

@PreviewLightDark
@Composable
private fun CharcoalRadioButtonPreview() {
    CharcoalM3Theme { CharcoalRadioButtonGallery() }
}

// endregion
