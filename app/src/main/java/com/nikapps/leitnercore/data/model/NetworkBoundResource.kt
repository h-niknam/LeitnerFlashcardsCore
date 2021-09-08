package com.nikapps.leitnerflashcardsmvp.data.model

import kotlinx.coroutines.flow.*
import java.lang.Exception


inline fun <ResultType, RequestType> networkBoundResources(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetched: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)){
        emit(Resource.Loading(data))
        try {
            saveFetched((fetch()))
            query().map { Resource.Success(it) }
        }catch (e : Exception){
            query().map { Resource.Error(e.message.toString(), it)}
        }
    }else{
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
