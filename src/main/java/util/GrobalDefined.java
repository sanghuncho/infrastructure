package util;

import java.util.HashMap;
import crawlerEntities.CategoryUsage;

public class GrobalDefined {
    public static final HashMap<String, String> grobalDefinedUsage = new HashMap<>() {
        {
            put("아이케어", "매일 아침, 저녁으로 피부결을 정돈후에 주름이 있는 눈가에 적당량을 덜어 부드럽게 펴 발라줍니다. 눈가외에 입주위, 미간 등과 같이 주름이 생기기 쉬운 부위에도 적당량을 발라주면 좋습니다.");
            
            //입욕제
            put("사해소금", "전신욕, 반신욕, 족욕에 모두 사용가능합니다. 따뜻한 온수에 적댱량을 덜어 녹여줍니다. 전신욕 3스푼, 반신옥 2스푼, 세안시 반스푼 정도가 적당하나 기호에 맞춰 조절해주시면 좋습니다.");

            put("bb크림", "");
            
            put("티트리오일", "여드름 부위에 면봉을 이용해서 가볍게 발라줍니다. 샴푸시 소량을 같이 사용하실수 있습니다. 세안시 몇방물 첨가해서 사용할수도 있습니다. 목욕이나 족욕시에도 적당량을 덜어 사용하시면 좋습니다.");
            
            put("수분크림", "세안후 또는 기초단계 이후에 적당량을 덜어서 얼굴에 펴 발라 흡수시켜줍니다.");
        }
    };
    
    public static final HashMap<String, CategoryUsage> categoryUsage = new HashMap<>() {
        {
            put("50000291", new CategoryUsage("입욕제", ""));
            
            put("50000470", new CategoryUsage("베이스메이크업", ""));
            
            put("50000297", new CategoryUsage("샴푸", "적당량을 덜어서 두피와 모발에 골고루 발라 마사지 하듯 부드럽게 거품을 내어 깨끗하게 헹궈주세요."));
            
            put("50000443", new CategoryUsage("페이스오일", ""));
            
            put("50000440", new CategoryUsage("크림", ""));
        }
    };
    
    public static final HashMap<String, CategoryUsage> categoryUsageCoopang = new HashMap<>() {
        {
            put("56163", new CategoryUsage("샴푸", ""));
            
            put("56288", new CategoryUsage("기능성샴푸", ""));
            
            put("56174", new CategoryUsage("일반오일", ""));

            put("56163", new CategoryUsage("데이크림", ""));
        }
    };
}
