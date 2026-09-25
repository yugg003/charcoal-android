package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import net.pixiv.charcoal.android.R
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 AlertDialog の既定色として提供する。
 * 生の [androidx.compose.material3.AlertDialog] の containerColor / iconContentColor / textContentColor に渡して使う。
 *
 * 文字スタイルは Typography（title = headlineSmall = bold20、text = bodyMedium = regular16、
 * ボタン = labelLarge = bold14）で当たるため、ここでは色だけを扱う。
 */
object CharcoalAlertDialogDefaults {
    /**
     * M3 既定(surfaceContainerHigh)と異なり surface1 に固定することで Charcoal の配色に合わせる。
     */
    val containerColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.surface1

    /**
     * M3 既定(secondary=brand)と異なり text3 に固定することで視覚的な重みを抑える。
     */
    val iconContentColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.text3

    /** AlertDialog タイトルのデフォルト色。 */
    val titleContentColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.text1

    /**
     * M3 既定(onSurfaceVariant=text2)と異なり text1 に固定することで本文の可読性を高める。
     */
    val textContentColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.text1
}

// region Preview

/**
 * CharcoalAlertDialogDefaults の全設定（containerColor / iconContentColor / titleContentColor / textContentColor）を
 * 素の AlertDialog に渡した状態。icon / title / text / confirm / dismiss をすべて持つ。
 */
@Composable
private fun CharcoalAlertDialogGallery() {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = { TextButton(onClick = {}) { Text("Confirm") } },
        dismissButton = { TextButton(onClick = {}) { Text("Dismiss") } },
        icon = { Icon(painter = painterResource(R.drawable.ic_charcoal_add_24), contentDescription = null) },
        title = { Text("Dialog title") },
        text = { Text("Dialog body text.") },
        containerColor = CharcoalAlertDialogDefaults.containerColor,
        iconContentColor = CharcoalAlertDialogDefaults.iconContentColor,
        titleContentColor = CharcoalAlertDialogDefaults.titleContentColor,
        textContentColor = CharcoalAlertDialogDefaults.textContentColor,
    )
}

@PreviewLightDark
@Composable
private fun CharcoalAlertDialogPreview() {
    CharcoalM3Theme { CharcoalAlertDialogGallery() }
}

// endregion
