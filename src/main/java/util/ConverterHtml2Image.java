package util;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

public class ConverterHtml2Image {
    public static void main(String[] args) throws Exception {
        String html = "<p style=\"text-align: center;\"><strong>르네휘테르 나뚜리아 샴푸 600ml</strong></p>\r\n" + 
                "<p style=\"text-align: center;\"><strong>르네휘테르</strong></p>\r\n" + 
                "<p style=\"text-align: center;\">Rene Furter 는 프랑스의 프로방스에 뿌리를 두고 있습니다.</p>\r\n" + 
                "<p style=\"text-align: center;\">식물이 비옥한 토양에서 가장 잘 자라는것처럼 모발도 좋은 두피에서 모발이 풍성해진다는 모토로 모발관리에 이상적인 헤어코스메틱입니다.</p>\r\n" + 
                "<p style=\"text-align: center;\">&nbsp;</p>\r\n" + 
                "<p style=\"text-align: center;\"><strong>아이템 </strong><strong>설명</strong></p>\r\n" + 
                "<p style=\"text-align: center;\">&nbsp;</p>\r\n" + 
                "<p style=\"text-align: center;\">부드러운 거풐과 식물 추출물이 두피를 깔끔하게 클렌징해주며 두피의 균형을 잡아주어</p>\r\n" + 
                "<p style=\"text-align: center;\">모발에 부드러움과 가벼움을 느끼게 해줍니다.</p>\r\n" + 
                "<p style=\"text-align: center;\">온 가족이 매일 사용할수 있는 샴푸로 모발에 수분을 공급해주고 부드럽고 윤기있는 모발을 만들어줍니다.</p>\r\n" + 
                "<p style=\"text-align: center;\"><strong>사용법</strong></p>\r\n" + 
                "<p style=\"text-align: center;\">적당량을 덜어서 두피와 모발에 골고루 발라 마사지 하듯 부드럽게 거품을 내어 깨끗하게 헹궈주세요.</p>\r\n" + 
                "<p style=\"text-align: center;\">&nbsp;</p>\r\n" + 
                "<p style=\"text-align: center;\"><strong>성분표</strong></p>\r\n" + 
                "<p style=\"text-align: center;\">WATER (AQUA), CETEARYL ALCOHOL, CAMELINA SATIVA SEED OIL, ETHYLHEXYL PALMITATE, GLYCOL PALMITATE, PROPYLENE GLYCOL, CETEARETH-33, BEHENYL ALCOHOL, GLYCERIN, HYDROLYZED WHEAT PROTEIN, ALPHA-ISOMETHYL IONONE, ARGININE HCL, BENZOIC ACID, CAPRYLYL GLYCOL, CARAMEL, CITRIC ACID, FRAGRANCE (PARFUM), GLYCINE SOJA (SOYBEAN) SEED EXTRACT (GLYCINE SOJA SEED EXTRACT), GLYCINE SOKA (SOYBEAN) SEEDCAKE EXTRACT (GLYCINE SOJA SEEDCAKE EXTRACT), GUAR HYDROXYPROPYLTRIMONIUM CHLORIDE, LIMONENE, LINALOOL, LYSINE HCL, PEG-32, PEG-400, POLYQUATERNIUM-37, SODIUM BENZOATE, TOCOPHEROL, TRISODIUM ETHYLENEDIAMINE DISUCCINATE, YELLOW 6 (CI 15985)</p>";
        int width = 500, height = 1000;

        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration()
            .createCompatibleImage(width, height);

        Graphics graphics = image.createGraphics();

        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);

        ImageIO.write(image, "png", new File("C:/Users/sanghuncho/Documents/GKoo_Store_Project/대량등록_엑셀_형식/Image.png"));
      }
}
