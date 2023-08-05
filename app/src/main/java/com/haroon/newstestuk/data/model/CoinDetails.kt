package com.haroon.newstestuk.data.model
import com.google.gson.annotations.SerializedName

data class CoinDetails(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("type") val type: String,
    @SerializedName("contract") val contract: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("contracts") val contracts: List<Contract>,
    @SerializedName("logo") val logo: String,
    @SerializedName("parent") val parent: ParentCoin,
    @SerializedName("tags") val tags: List<Tag>,
    @SerializedName("team") val team: List<String>,
    @SerializedName("description") val description: String,
    @SerializedName("message") val message: String?,
    @SerializedName("open_source") val openSource: Boolean,
    @SerializedName("started_at") val startedAt: String?,
    @SerializedName("development_status") val developmentStatus: String?,
    @SerializedName("hardware_wallet") val hardwareWallet: Boolean,
    @SerializedName("proof_type") val proofType: String?,
    @SerializedName("org_structure") val orgStructure: String?,
    @SerializedName("hash_algorithm") val hashAlgorithm: String?,
    @SerializedName("links") val links: Links,
    @SerializedName("links_extended") val linksExtended: List<LinkExtended>,
    @SerializedName("whitepaper") val whitepaper: Whitepaper,
    @SerializedName("first_data_at") val firstDataAt: String?,
    @SerializedName("last_data_at") val lastDataAt: String?
)

data class Contract(
    @SerializedName("contract") val contract: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("type") val type: String
)

data class ParentCoin(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
)

data class Tag(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("coin_counter") val coinCounter: Int,
    @SerializedName("ico_counter") val icoCounter: Int
)

data class Links(
    @SerializedName("website") val website: List<String>
)

data class LinkExtended(
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String
)

data class Whitepaper(
    @SerializedName("link") val link: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)
