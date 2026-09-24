package net.pixiv.charcoal.android.compose.m3.theme

import androidx.compose.material3.Typography
import net.pixiv.charcoal.android.compose.theme.CharcoalTypography

/**
 * Charcoal のタイポグラフィを M3 の [Typography] ロールに割り当てる。
 *
 * M3 コンポーネントは文字スタイルを `MaterialTheme.typography` の各ロールから取るため、
 * ここで割り当てておけば個々のコンポーネントで LocalTextStyle を差し替える必要がなくなる。
 * 1 ロールにつき 1 スタイルしか持てないので、同じロールを使うコンポーネントはすべて同じスタイルになる。
 *
 * 方針: 文字スタイルの差分は、**可能な限り類似する Charcoal のスタイルを M3 のロールにマッピングして吸収する**。
 * コンポーネント個別に LocalTextStyle を差し替えたり専用コンポーネントを用意したりするのは、
 * 同じロールを共有する他のコンポーネントと要求が食い違い、ロールでは表現できない場合の最後の手段とする
 * （例: Chip だけ bold12 にしたい場合。labelLarge は Button 系と共有しているため呼び出し側で明示する）。
 *
 * ロールごとの影響範囲と設定値（material3 1.4.0 のトークンから。Charcoal の字間は未指定 = フォント既定）:
 *
 * | M3 ロール | 影響するコンポーネント（material3 1.4.0） | M3 既定 | 調整後（Charcoal） |
 * |---|---|---|---|
 * | headlineSmall | AlertDialog の title / MediumTopAppBar の title | Regular 24sp / 行高 32sp / 字間 0 | bold20: Bold 20sp / 行高 28sp / 字間なし |
 * | titleLarge | TopAppBar の title / DatePicker のヘッダ | Regular 22sp / 行高 28sp / 字間 0 | regular20: Regular 20sp / 行高 28sp / 字間なし |
 * | titleMedium | ListItem の headline / LargeFlexibleTopAppBar / TimePicker / TimeInput | Medium 16sp / 行高 24sp / 字間 0.15sp | regular16: Regular 16sp / 行高 24sp / 字間なし |
 * | titleSmall | Tab のラベル / NavigationDrawer / DatePicker / RichTooltip | Medium 14sp / 行高 20sp / 字間 0.1sp | regular14: Regular 14sp / 行高 22sp / 字間なし |
 * | bodyLarge | 既定の Text（LocalTextStyle）/ TextField / SearchBar / ListItem / DatePicker / TimePicker | Regular 16sp / 行高 24sp / 字間 0.5sp | regular14: Regular 14sp / 行高 22sp / 字間なし |
 * | bodyMedium | AlertDialog の text / ListItem の supporting text / Snackbar / RichTooltip | Regular 14sp / 行高 20sp / 字間 0.25sp | regular16: Regular 16sp / 行高 24sp / 字間なし |
 * | bodySmall | TextField の supporting text / PlainTooltip / TimeInput | Regular 12sp / 行高 16sp / 字間 0.4sp | regular12: Regular 12sp / 行高 20sp / 字間なし |
 * | labelLarge | Button / TextButton / Chip / Dialog のボタン / NavigationDrawer / Snackbar のアクション / Slider / SegmentedButton / ExtendedFab | Medium 14sp / 行高 20sp / 字間 0.1sp | bold14: Bold 14sp / 行高 22sp / 字間なし |
 * | labelMedium | NavigationBar / NavigationRail のラベル / TopAppBar の subtitle / TimePicker | Medium 12sp / 行高 16sp / 字間 0.5sp | regular12: Regular 12sp / 行高 20sp / 字間なし |
 * | labelSmall | Badge / ListItem の trailing | Medium 11sp / 行高 16sp / 字間 0.5sp | regular10: Regular 10sp / 行高 18sp / 字間なし |
 *
 * 未設定（M3 既定のまま）:
 *
 * | M3 ロール | 影響するコンポーネント | M3 既定 | 理由 |
 * |---|---|---|---|
 * | displayLarge / displayMedium / displaySmall | TimePicker / TimeInput の数字、LargeFlexibleTopAppBar | 57 / 45 / 36sp | Charcoal に対応サイズなし |
 * | headlineLarge / headlineMedium | DatePicker の見出し、LargeTopAppBar / MediumFlexibleTopAppBar の title | 32 / 28sp | 同上 |
 *
 * M3 の Text は LocalTextStyle と引数の style をマージするため、Charcoal 側で未指定の項目（字間など）は
 * ロールの値ではなく M3 既定の LocalTextStyle（platformStyle / lineHeightStyle）を引き継ぐ。
 *
 * @param typography 元になる Charcoal のタイポグラフィ
 */
internal fun charcoalM3Typography(typography: CharcoalTypography): Typography {
    val default = Typography()
    return default.copy(
        headlineSmall = typography.bold20,
        titleLarge = typography.regular20,
        titleMedium = typography.regular16,
        titleSmall = typography.regular14,
        bodyLarge = typography.regular14,
        bodyMedium = typography.regular16,
        bodySmall = typography.regular12,
        labelLarge = typography.bold14,
        labelMedium = typography.regular12,
        labelSmall = typography.regular10,
    )
}
