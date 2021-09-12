#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_kesegotumisang_apptest_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_kesegotumisang_apptest_api_ApiClient_baseUrl(JNIEnv *env, jobject thiz) {
    std::string baseUrl = "http://api.nytimes.com/svc/";
    return env->NewStringUTF(baseUrl.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_kesegotumisang_apptest_MainActivity_apiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "77Kkm0buO2bvoMwTxvwBJD6qamVwWtyP";
    return env->NewStringUTF(apiKey.c_str());
}