#pragma version(1)
#pragma rs java_package_name(com.xy.activity)
#pragma rs_fp_full

const static float3 weight = {0.299f,0.587f,0.114f};
float saturationValue = 0.f;

uchar4 __attribute__((kernel)) saturation(uchar4 in){
        float4 f4 = rsUnpackColor8888(in);
        float3 result = dot(f4.rgb, weight);
        result = mix(result, f4.rgb, saturationValue);
        return rsPackColorTo8888(result);
}


