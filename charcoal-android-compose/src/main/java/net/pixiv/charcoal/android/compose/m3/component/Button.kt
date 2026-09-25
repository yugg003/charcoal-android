package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.compose.extension.overlayDisabledAlpha
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewRow
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalContentAlpha
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンで配色・形状・サイズを統一した Surface ベースのボタン。
 * 配色パターン (primary/default/text) は [colors] 引数で選択し、
 * サイズ種別 (M/S) は [buttonSize] 引数で切り替える。
 *
 * あえて M3 の [androidx.compose.material3.Button] に委譲せず Surface で自作しているのは、
 * M3 Button が内部で `defaultMinSize(minHeight = ButtonDefaults.MinHeight = 40.dp)` を強制し、
 * 引数で下限を下げられないため。Charcoal の S サイズ (minHeight 32dp) を再現するには
 * minHeight を自前で制御する必要があり、Surface ベースで組んでいる。
 * また Charcoal は Elevation を扱わないため tonalElevation / shadowElevation は 0 固定。
 */
@Composable
fun CharcoalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CharcoalButtonDefaults.shape,
    colors: ButtonColors = CharcoalButtonDefaults.defaultButtonColors(),
    border: BorderStroke? = null,
    buttonSize: CharcoalButtonSize = CharcoalButtonDefaults.buttonSizeM(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    androidx.compose.material3.Surface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = border,
        interactionSource = interactionSource,
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            ProvideTextStyle(value = CharcoalTheme.typography.bold14) {
                Row(
                    Modifier
                        .defaultMinSize(minHeight = buttonSize.minHeight)
                        .padding(buttonSize.contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content,
                )
            }
        }
    }
}

/**
 * Charcoal の配色・形状を既定値にしたテキストボタン。M3 の [androidx.compose.material3.TextButton] に委譲する。
 * 背景は透明・contentColor は brand がデフォルト。
 * テキストリンク的な操作や低強調アクションに使用する。
 */
@Composable
fun CharcoalTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CharcoalButtonDefaults.textShape,
    colors: ButtonColors = CharcoalButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = CharcoalButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    androidx.compose.material3.TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

/**
 * ボタンの最小高さとコンテンツパディングを保持するサイズ種別クラス。
 * [CharcoalButtonDefaults.buttonSizeM] / [CharcoalButtonDefaults.buttonSizeS] で取得する。
 */
@Immutable
class CharcoalButtonSize internal constructor(
    val minHeight: Dp,
    val contentPadding: PaddingValues,
)

/** Charcoal Button の既定値・ファクトリをまとめたオブジェクト。 */
object CharcoalButtonDefaults {
    /** 通常ボタン (CharcoalButton) のデフォルト形状。CircleShape を使用する。 */
    val shape: Shape @Composable get() = CircleShape

    /** テキストボタン (CharcoalTextButton) のデフォルト形状。CircleShape を使用する。 */
    val textShape: Shape @Composable get() = CircleShape

    /** テキストボタンのデフォルトコンテンツパディング。 */
    val TextButtonContentPadding =
        PaddingValues(
            start = 12.dp,
            top = 8.dp,
            end = 12.dp,
            bottom = 8.dp,
        )

    /**
     * brand 背景・text5 テキストの Primary ボタン配色を生成する。
     * CTA など最重要アクションに使用する。
     */
    @Composable
    fun primaryButtonColors(
        containerColor: Color = CharcoalTheme.colorToken.brand,
        contentColor: Color = CharcoalTheme.colorToken.text5,
    ): ButtonColors {
        return generateCharcoalButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    }

    /**
     * surface3 背景・text2 テキストの Default ボタン配色を生成する。
     * Secondary アクション（tonal 相当を含む）に使用する。
     */
    @Composable
    fun defaultButtonColors(
        containerColor: Color = CharcoalTheme.colorToken.surface3,
        contentColor: Color = CharcoalTheme.colorToken.text2,
    ): ButtonColors {
        return generateCharcoalButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    }

    /**
     * 透明背景・brand テキストのテキストボタン配色を生成する。
     * 低強調アクションやテキストリンク的な操作に使用する。
     */
    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CharcoalTheme.colorToken.brand,
    ): ButtonColors {
        return generateCharcoalButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        )
    }

    /**
     * 高さ 40dp・水平パディング 24dp の M サイズを生成する。
     * 標準的なボタンサイズ。
     */
    @Composable
    fun buttonSizeM(
        minHeight: Dp = charcoalButtonSizeMMinHeight,
        contentPadding: PaddingValues = charcoalButtonSizeMContentPadding,
    ): CharcoalButtonSize {
        return CharcoalButtonSize(
            minHeight = minHeight,
            contentPadding = contentPadding,
        )
    }

    /**
     * 高さ 32dp・水平パディング 16dp の S サイズを生成する。
     * スペースが限られた場所や補助的なボタンに使用する。
     */
    @Composable
    fun buttonSizeS(
        minHeight: Dp = charcoalButtonSizeSMinHeight,
        contentPadding: PaddingValues = charcoalButtonSizeSContentPadding,
    ): CharcoalButtonSize {
        return CharcoalButtonSize(
            minHeight = minHeight,
            contentPadding = contentPadding,
        )
    }

    /**
     * disabled 色を [CharcoalContentAlpha.disabled] で算出した M3 [ButtonColors] を生成する。
     * disabled コンテンツ色は surface1 に compositeOver することで読み取り可能なコントラストを維持する。
     */
    @Composable
    fun generateCharcoalButtonColors(
        containerColor: Color,
        contentColor: Color,
    ): ButtonColors {
        return ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.overlayDisabledAlpha(),
            disabledContentColor = contentColor.overlayDisabledAlpha()
                .compositeOver(CharcoalTheme.colorToken.surface1),
        )
    }

    /**
     * M3 [ButtonDefaults.buttonElevation] から生成した [ButtonElevation]。
     * Charcoal Button は通常 Elevation を使わないため全段階 0dp を既定とする。
     */
    @Composable
    fun buttonElevation(
        defaultElevation: Dp = 0.dp,
        pressedElevation: Dp = 0.dp,
        focusedElevation: Dp = 0.dp,
        hoveredElevation: Dp = 0.dp,
        disabledElevation: Dp = 0.dp,
    ): ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        disabledElevation = disabledElevation,
    )
}

private val charcoalButtonSizeSMinHeight = 32.dp
private val charcoalButtonSizeMMinHeight = 40.dp
private val charcoalButtonSizeSContentPadding = PaddingValues(horizontal = 16.dp)
private val charcoalButtonSizeMContentPadding = PaddingValues(horizontal = 24.dp)

// region Preview

/**
 * CharcoalButton / CharcoalTextButton と CharcoalButtonDefaults の全設定。
 * 配色（primary / default / text / generateCharcoalButtonColors）× サイズ（M / S）× enabled / disabled。
 */
@Composable
private fun CharcoalButtonGallery() {
    CharcoalPreviewColumn {
        Text("primaryButtonColors × buttonSizeM / buttonSizeS")
        CharcoalPreviewRow {
            CharcoalButton(onClick = {}, colors = CharcoalButtonDefaults.primaryButtonColors()) { Text("Primary M") }
            CharcoalButton(
                onClick = {},
                colors = CharcoalButtonDefaults.primaryButtonColors(),
                buttonSize = CharcoalButtonDefaults.buttonSizeS(),
            ) { Text("Primary S") }
            CharcoalButton(
                onClick = {},
                enabled = false,
                colors = CharcoalButtonDefaults.primaryButtonColors(),
            ) { Text("Disabled") }
        }
        Text("defaultButtonColors × buttonSizeM / buttonSizeS")
        CharcoalPreviewRow {
            CharcoalButton(onClick = {}) { Text("Default M") }
            CharcoalButton(onClick = {}, buttonSize = CharcoalButtonDefaults.buttonSizeS()) { Text("Default S") }
            CharcoalButton(onClick = {}, enabled = false) { Text("Disabled") }
        }
        Text("textButtonColors（CharcoalTextButton）")
        CharcoalPreviewRow {
            CharcoalTextButton(onClick = {}) { Text("Text button") }
            CharcoalTextButton(onClick = {}, enabled = false) { Text("Disabled") }
        }
        Text("generateCharcoalButtonColors（assertive / text5）")
        CharcoalPreviewRow {
            CharcoalButton(
                onClick = {},
                colors = CharcoalButtonDefaults.generateCharcoalButtonColors(
                    containerColor = CharcoalTheme.colorToken.assertive,
                    contentColor = CharcoalTheme.colorToken.text5,
                ),
            ) { Text("Custom") }
            CharcoalButton(
                onClick = {},
                enabled = false,
                colors = CharcoalButtonDefaults.generateCharcoalButtonColors(
                    containerColor = CharcoalTheme.colorToken.assertive,
                    contentColor = CharcoalTheme.colorToken.text5,
                ),
            ) { Text("Disabled") }
        }
        Text("shape / textShape は CircleShape、buttonElevation は全状態 0dp")
    }
}

@PreviewLightDark
@Composable
private fun CharcoalButtonPreview() {
    CharcoalM3Theme { CharcoalButtonGallery() }
}

// endregion
