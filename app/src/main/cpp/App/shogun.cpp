#include <jni.h>
#include <string>
#include <sstream>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
using namespace std;

std::string getPublicStaticString(JNIEnv *env, const char *className, const char *fieldName) {
    jclass clazz = env->FindClass(className);
    if (clazz != nullptr) {
        jfieldID fid = env->GetStaticFieldID(clazz, fieldName, "Ljava/lang/String;");
        if (fid != nullptr) {
            jstring GladioReceiver = (jstring) env->GetStaticObjectField(clazz, fid);
            jboolean blnIsCopy;
            std::string mystr = env->GetStringUTFChars(GladioReceiver, &blnIsCopy);
            return mystr;
        }
    }
    return "ERROR";
}

std::string CreateUniqueDeviceID(JNIEnv *env) {
    std::string strReturn;
    std::string board = getPublicStaticString(env, "android/os/Build", "BOARD");
    std::string brand = getPublicStaticString(env, "android/os/Build", "BRAND");
    std::string display = getPublicStaticString(env, "android/os/Build", "DISPLAY");
    std::string device = getPublicStaticString(env, "android/os/Build", "DEVICE");
    std::string manufacturer = getPublicStaticString(env, "android/os/Build", "MANUFACTURER");
    std::string model = getPublicStaticString(env, "android/os/Build", "MODEL");
    std::string product = getPublicStaticString(env, "android/os/Build", "PRODUCT");

    int mod = 8;
    int a1 = ((int) board.size()) % mod;
    int a2 = ((int) brand.size()) % mod;
    int a3 = ((int) display.size()) % mod;
    int a4 = ((int) device.size()) % mod;
    int a5 = ((int) manufacturer.size()) % mod;
    int a6 = ((int) model.size()) % mod;
    int a7 = ((int) product.size()) % mod;

    strReturn =
            std::to_string(a1) +
            std::to_string(a2) +
            std::to_string(a3) +
            std::to_string(a4) +
            std::to_string(a5) +
            std::to_string(a6) +
            std::to_string(a7);
    return strReturn;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_lolideveloper_shogun_Core_Loader_00024Companion_getID(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(CreateUniqueDeviceID(env).c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_lolideveloper_shogun_Ui_View_Menu_Svip_Fg1_1svp_value(JNIEnv *env, jobject thiz, jint i) {
    switch (i) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
    }
}