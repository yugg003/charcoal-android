package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.compose.extension.overlayDisabledAlpha
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewRow
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 Checkbox のデフォルト色として提供する。
 * charcoal-android-view の Widget.Charcoal.CompoundButton.CheckBox (buttonTint) に合わせ、
 * checked=brand / unchecked=text3 とする。unchecked は M3 既定の onSurfaceVariant(=text2) と
 * 異なるため、ColorScheme だけでは再現できず明示指定が必要。
 * 生の [androidx.compose.material3.Checkbox] に渡して使う。
 */
object CharcoalCheckboxDefaults {

    /**
     * Charcoal トークンを既定値にした [CheckboxColors] を返す。各色は引数で差し替えられる。
     * disabled 色の既定値は対応する有効時の色から算出する。
     */
    @Composable
    fun colors(
        checkedColor: Color = CharcoalTheme.colorToken.brand,
        uncheckedColor: Color = CharcoalTheme.colorToken.text3,
        checkmarkColor: Color = CharcoalTheme.colorToken.text5,
        disabledCheckedColor: Color = checkedColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledUncheckedColor: Color = uncheckedColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledIndeterminateColor: Color = checkedColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
    ): CheckboxColors = CheckboxDefaults.colors(
        checkedColor = checkedColor,
        uncheckedColor = uncheckedColor,
        checkmarkColor = checkmarkColor,
        disabledCheckedColor = disabledCheckedColor,
        disabledUncheckedColor = disabledUncheckedColor,
        disabledIndeterminateColor = disabledIndeterminateColor,
    )
}

// region Preview

/**
 * CharcoalCheckboxDefaults.colors() の全設定。
 * checked / unchecked / indeterminate × enabled / disabled（checkedColor / uncheckedColor / checkmarkColor /
 * disabledCheckedColor / disabledUncheckedColor / disabledIndeterminateColor）。
 */
@Composable
private fun CharcoalCheckboxGallery() {
    CharcoalPreviewColumn {
        Text("enabled: checked / unchecked / indeterminate")
        CharcoalPreviewRow {
            Checkbox(checked = true, onCheckedChange = {}, colors = CharcoalCheckboxDefaults.colors())
            Checkbox(checked = false, onCheckedChange = {}, colors = CharcoalCheckboxDefaults.colors())
            TriStateCheckbox(
                state = ToggleableState.Indeterminate,
                onClick = {},
                colors = CharcoalCheckboxDefaults.colors(),
            )
        }
        Text("disabled: checked / unchecked / indeterminate")
        CharcoalPreviewRow {
            Checkbox(checked = true, onCheckedChange = {}, enabled = false, colors = CharcoalCheckboxDefaults.colors())
            Checkbox(checked = false, onCheckedChange = {}, enabled = false, colors = CharcoalCheckboxDefaults.colors())
            TriStateCheckbox(
                state = ToggleableState.Indeterminate,
                onClick = {},
                enabled = false,
                colors = CharcoalCheckboxDefaults.colors(),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CharcoalCheckboxPreview() {
    CharcoalM3Theme { CharcoalCheckboxGallery() }
}

// endregion
