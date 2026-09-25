package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.R
import net.pixiv.charcoal.android.compose.extension.overlayDisabledAlpha
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewRow
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 Switch のデフォルト色として提供する。
 * uncheckedTrack は surface3.compositeOver(surface1) を維持（surface3_solid とは別色）。
 * disabled 色は surface1 に compositeOver して白抜けを防ぐ。
 * 生の [androidx.compose.material3.Switch] に渡して使う。
 */
object CharcoalSwitchDefaults {

    /**
     * Charcoal トークンを既定値にした [SwitchColors] を返す。各色は引数で差し替えられる。
     * disabled 色の既定値は対応する有効時の色から算出する。
     */
    @Composable
    fun colors(
        checkedThumbColor: Color = CharcoalTheme.colorToken.text5,
        checkedTrackColor: Color = CharcoalTheme.colorToken.brand,
        checkedBorderColor: Color = CharcoalTheme.colorToken.brand,
        checkedIconColor: Color = Color.Transparent,
        uncheckedThumbColor: Color = CharcoalTheme.colorToken.text3,
        uncheckedTrackColor: Color = CharcoalTheme.colorToken.surface3
            .compositeOver(CharcoalTheme.colorToken.surface1),
        uncheckedBorderColor: Color = CharcoalTheme.colorToken.text3,
        uncheckedIconColor: Color = Color.Transparent,
        disabledCheckedThumbColor: Color = checkedThumbColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledCheckedTrackColor: Color = checkedTrackColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledCheckedBorderColor: Color = checkedBorderColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledCheckedIconColor: Color = checkedIconColor,
        disabledUncheckedThumbColor: Color = uncheckedThumbColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledUncheckedTrackColor: Color = uncheckedTrackColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledUncheckedBorderColor: Color = uncheckedBorderColor
            .overlayDisabledAlpha()
            .compositeOver(CharcoalTheme.colorToken.surface1),
        disabledUncheckedIconColor: Color = uncheckedIconColor,
    ): SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor,
        checkedBorderColor = checkedBorderColor,
        checkedIconColor = checkedIconColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedBorderColor = uncheckedBorderColor,
        uncheckedIconColor = uncheckedIconColor,
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor,
        disabledCheckedBorderColor = disabledCheckedBorderColor,
        disabledCheckedIconColor = disabledCheckedIconColor,
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor,
        disabledUncheckedBorderColor = disabledUncheckedBorderColor,
        disabledUncheckedIconColor = disabledUncheckedIconColor,
    )
}

// region Preview

/**
 * CharcoalSwitchDefaults.colors() の全設定。
 * checked / unchecked × enabled / disabled（thumb / track / border 色）と、thumbContent を置いた場合の icon 色
 * （checkedIconColor / uncheckedIconColor / disabledCheckedIconColor / disabledUncheckedIconColor は Transparent）。
 */
@Composable
private fun CharcoalSwitchGallery() {
    CharcoalPreviewColumn {
        Text("enabled: checked / unchecked")
        CharcoalPreviewRow {
            Switch(checked = true, onCheckedChange = {}, colors = CharcoalSwitchDefaults.colors())
            Switch(checked = false, onCheckedChange = {}, colors = CharcoalSwitchDefaults.colors())
        }
        Text("disabled: checked / unchecked")
        CharcoalPreviewRow {
            Switch(checked = true, onCheckedChange = {}, enabled = false, colors = CharcoalSwitchDefaults.colors())
            Switch(checked = false, onCheckedChange = {}, enabled = false, colors = CharcoalSwitchDefaults.colors())
        }
        Text("thumbContent あり（icon 色は Transparent）")
        CharcoalPreviewRow {
            Switch(
                checked = true,
                onCheckedChange = {},
                thumbContent = { ThumbIcon() },
                colors = CharcoalSwitchDefaults.colors(),
            )
            Switch(
                checked = false,
                onCheckedChange = {},
                thumbContent = { ThumbIcon() },
                colors = CharcoalSwitchDefaults.colors(),
            )
            Switch(
                checked = true,
                onCheckedChange = {},
                enabled = false,
                thumbContent = { ThumbIcon() },
                colors = CharcoalSwitchDefaults.colors(),
            )
            Switch(
                checked = false,
                onCheckedChange = {},
                enabled = false,
                thumbContent = { ThumbIcon() },
                colors = CharcoalSwitchDefaults.colors(),
            )
        }
    }
}

@Composable
private fun ThumbIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_charcoal_add_24),
        contentDescription = null,
        modifier = Modifier.size(SwitchDefaults.IconSize),
    )
}

@PreviewLightDark
@Composable
private fun CharcoalSwitchPreview() {
    CharcoalM3Theme { CharcoalSwitchGallery() }
}

// endregion
