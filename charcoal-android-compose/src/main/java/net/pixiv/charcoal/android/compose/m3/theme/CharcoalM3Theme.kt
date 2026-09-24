package net.pixiv.charcoal.android.compose.m3.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import net.pixiv.charcoal.android.compose.theme.CharcoalColorToken
import net.pixiv.charcoal.android.compose.theme.CharcoalTypography
import net.pixiv.charcoal.android.compose.theme.LocalCharcoalColorToken
import net.pixiv.charcoal.android.compose.theme.LocalCharcoalTypography
import net.pixiv.charcoal.android.compose.theme.charcoalDarkColorToken
import net.pixiv.charcoal.android.compose.theme.charcoalLightColorToken

/**
 * Charcoal のトークンから組み立てた Material3 の設定を適用する Theme
 *
 * Material2 版の [net.pixiv.charcoal.android.compose.theme.CharcoalTheme] と同じく
 * Charcoal のカラートークンとタイポグラフィを CompositionLocal で提供する。
 * 両者は排他で、どちらか一方だけを適用すること。
 * トークンへのアクセスは Material2 版と共通の
 * [net.pixiv.charcoal.android.compose.theme.CharcoalTheme] object を使う。
 *
 * MaterialTheme に渡す設定と、あわせて提供する CompositionLocal:
 *
 * - ColorScheme: Charcoal のトークンを M3 のロールに割り当てたもの（`charcoalM3ColorScheme`）。
 *   Tonal Elevation は無効化する（`LocalTonalElevationEnabled = false`）
 * - Typography: Charcoal のスタイルを M3 のロールに割り当てたもの（`charcoalM3Typography`）。
 *   既定の TextStyle は bodyLarge = regular14
 * - ripple: Charcoal の surface10 で描く（`charcoalRippleConfiguration`）
 * - テキスト選択色: Charcoal の brand で描く（`rememberCharcoalTextSelectionColors`）
 *
 * 背景色ごとの既定の文字色（`contentColorFor` の解決結果）は Charcoal の仕様として固定している。
 * 表と違う色にしたい場合は呼び出し側で contentColor / color を明示する。
 * 対応表は `charcoalM3ColorScheme` のドキュメントを参照。
 *
 * @param isDarkTheme true でダークテーマ、false でライトテーマを適用する
 * @param colorToken 適用する Charcoal のカラートークン
 * @param content テーマを適用したい Composable
 */
@Composable
fun CharcoalM3Theme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colorToken: CharcoalColorToken = if (isDarkTheme) charcoalDarkColorToken() else charcoalLightColorToken(),
    content: @Composable () -> Unit,
) {
    // Charcoal 側の定義
    val charcoalColorToken = colorToken
    val charcoalTypography = CharcoalTypography()

    // Charcoal の定義から組み立てた M3 側の設定
    val m3ColorScheme = charcoalM3ColorScheme(isDarkTheme = isDarkTheme, colorToken = charcoalColorToken)
    val m3Typography = charcoalM3Typography(charcoalTypography)
    val m3RippleConfiguration = charcoalRippleConfiguration(charcoalColorToken)
    val m3TextSelectionColors = rememberCharcoalTextSelectionColors(charcoalColorToken)

    // MaterialTheme はテキスト選択色などを自身の ColorScheme から提供するため、
    // Charcoal の値で上書きできるよう CompositionLocal はすべて MaterialTheme の内側で提供する
    MaterialTheme(
        colorScheme = m3ColorScheme,
        typography = m3Typography,
    ) {
        CompositionLocalProvider(
            LocalCharcoalColorToken provides charcoalColorToken,
            LocalCharcoalTypography provides charcoalTypography,
            LocalTonalElevationEnabled provides false,
            LocalRippleConfiguration provides m3RippleConfiguration,
            LocalTextSelectionColors provides m3TextSelectionColors,
            content = content,
        )
    }
}

/**
 * Charcoal の surface10（タッチフィードバック色）で ripple を描く [RippleConfiguration]。
 *
 * M3 の既定は contentColor を状態ごとの不透明度（pressed 0.1 など）で重ねるが、
 * Charcoal はタッチフィードバック専用の半透明トークンを持つため、色を surface10 に固定し、
 * 不透明度はトークンの alpha を pressed / focused / hovered / dragged のすべてに使う。
 * ripple の Indication 自体は MaterialTheme が提供するものをそのまま使い、設定だけを差し替える。
 */
private fun charcoalRippleConfiguration(colorToken: CharcoalColorToken): RippleConfiguration {
    val touchFeedbackColor = colorToken.surface10
    return RippleConfiguration(
        color = touchFeedbackColor,
        rippleAlpha = RippleAlpha(
            draggedAlpha = touchFeedbackColor.alpha,
            focusedAlpha = touchFeedbackColor.alpha,
            hoveredAlpha = touchFeedbackColor.alpha,
            pressedAlpha = touchFeedbackColor.alpha,
        ),
    )
}

/** 選択範囲の背景に使う brand の不透明度。 */
private const val TEXT_SELECTION_BACKGROUND_ALPHA = 0.4f

/**
 * Charcoal の brand でテキスト選択色を描く [TextSelectionColors]。
 *
 * ハンドルは brand、選択範囲の背景は brand の 40%。MaterialTheme は ColorScheme.primary から
 * 同じ値を作るが、ColorScheme の割り当てに依存させず Charcoal のトークンから明示的に提供する。
 * MaterialTheme が提供する値を上書きするため、MaterialTheme の内側で LocalTextSelectionColors に渡す。
 */
@Composable
private fun rememberCharcoalTextSelectionColors(colorToken: CharcoalColorToken): TextSelectionColors {
    val brand = colorToken.brand
    return remember(brand) {
        TextSelectionColors(
            handleColor = brand,
            backgroundColor = brand.copy(alpha = TEXT_SELECTION_BACKGROUND_ALPHA),
        )
    }
}
