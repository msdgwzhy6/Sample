package luyao.sample.mergeadapter.data

sealed class LoadState {
    object Loading:LoadState()
    object Success:LoadState()
    object End:LoadState()
}