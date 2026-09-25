package net.pixiv.charcoal.android.compose.m3.component

import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.pixiv.charcoal.android.R
import net.pixiv.charcoal.android.compose.m3.preview.CharcoalPreviewColumn
import net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme
import net.pixiv.charcoal.android.compose.theme.CharcoalTheme

/**
 * Charcoal デザイントークンを M3 Tab / SecondaryTabRow の既定値として提供する。
 * 生の [androidx.compose.material3.Tab] と [androidx.compose.material3.SecondaryTabRow] に渡して使う。
 *
 * - 非選択 Tab を text3 にするのは、M3 既定（選択 Tab と同色 = LocalContentColor）から意図的に逸脱する
 *   Charcoal 固有値で、ColorScheme では表現できないためここで提供する。
 * - インジケータは Charcoal の定義で高さ 2dp（M3 既定は 3dp）。[Indicator] を SecondaryTabRow の
 *   indicator に渡す。
 *
 * TabRow の container / content / divider は
 * [CharcoalM3Theme][net.pixiv.charcoal.android.compose.m3.theme.CharcoalM3Theme] の ColorScheme
 * （surface / onSurface / outlineVariant）で既定値がそのまま Charcoal 色になる。
 *
 * ```
 * SecondaryTabRow(
 *     selectedTabIndex = index,
 *     indicator = { CharcoalTabDefaults.Indicator(Modifier.tabIndicatorOffset(index)) },
 * ) {
 *     Tab(
 *         selected = ..., onClick = ...,
 *         selectedContentColor = CharcoalTabDefaults.selectedContentColor,
 *         unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
 *         text = { Text("...") },
 *     )
 * }
 * ```
 */
object CharcoalTabDefaults {
    /** 選択中 Tab のコンテンツ色。 */
    val selectedContentColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.text1

    /** 非選択 Tab のコンテンツ色。 */
    val unselectedContentColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.text3

    /** 選択中 Tab のインジケータ色。 */
    val indicatorColor: Color @Composable @ReadOnlyComposable get() = CharcoalTheme.colorToken.brand

    /** 選択中 Tab のインジケータの高さ。Charcoal の定義は 2dp（M3 既定の 3dp と異なる）。 */
    val IndicatorHeight: Dp = 2.dp

    /**
     * Charcoal の色・高さを当てた Tab のインジケータ。
     * SecondaryTabRow の `indicator` で `Modifier.tabIndicatorOffset(selectedTabIndex)` を渡して使う。
     */
    @Composable
    fun Indicator(
        modifier: Modifier = Modifier,
        color: Color = indicatorColor,
    ) {
        TabRowDefaults.SecondaryIndicator(
            modifier = modifier,
            height = IndicatorHeight,
            color = color,
        )
    }
}

// region Preview

/**
 * CharcoalTabDefaults の全設定。
 * SecondaryTabRow の indicator（Indicator / IndicatorHeight / indicatorColor）と、
 * Tab / LeadingIconTab の selectedContentColor / unselectedContentColor（selected / unselected / disabled）。
 */
@Composable
private fun CharcoalTabGallery() {
    CharcoalPreviewColumn {
        Text("Tab（text スロット）")
        SecondaryTabRow(
            selectedTabIndex = 0,
            indicator = { CharcoalTabDefaults.Indicator(Modifier.tabIndicatorOffset(0)) },
        ) {
            Tab(
                selected = true,
                onClick = {},
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
                text = { Text("Selected") },
            )
            Tab(
                selected = false,
                onClick = {},
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
                text = { Text("Tab") },
            )
            Tab(
                selected = false,
                onClick = {},
                enabled = false,
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
                text = { Text("Disabled") },
            )
        }
        Text("LeadingIconTab")
        SecondaryTabRow(
            selectedTabIndex = 1,
            indicator = { CharcoalTabDefaults.Indicator(Modifier.tabIndicatorOffset(1)) },
        ) {
            LeadingIconTab(
                selected = false,
                onClick = {},
                text = { Text("Off") },
                icon = { TabIcon() },
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
            )
            LeadingIconTab(
                selected = true,
                onClick = {},
                text = { Text("On") },
                icon = { TabIcon() },
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
            )
            LeadingIconTab(
                selected = false,
                onClick = {},
                text = { Text("N/A") },
                icon = { TabIcon() },
                enabled = false,
                selectedContentColor = CharcoalTabDefaults.selectedContentColor,
                unselectedContentColor = CharcoalTabDefaults.unselectedContentColor,
            )
        }
    }
}

@Composable
private fun TabIcon() {
    Icon(painter = painterResource(R.drawable.ic_charcoal_add_24), contentDescription = null)
}

@PreviewLightDark
@Composable
private fun CharcoalTabPreview() {
    CharcoalM3Theme { CharcoalTabGallery() }
}

// endregion
