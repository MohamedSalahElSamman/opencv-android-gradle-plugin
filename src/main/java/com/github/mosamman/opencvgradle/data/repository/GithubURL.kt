package com.github.mosamman.opencvgradle.data.repository

@Suppress("SpellCheckingInspection")
object GithubURL {
    const val GITHUB_API = "https://api.github.com/"
    private const val GITHUB_REPO = "android-opencv"
    private const val GITHUB_USERNAME =  "MohamedSalahElSamman"

    const val RELEASES_LIST = "repos/$GITHUB_USERNAME/$GITHUB_REPO/releases"
    const val ASSET_BASE = "repos/$GITHUB_USERNAME/$GITHUB_REPO/releases/assets"
}