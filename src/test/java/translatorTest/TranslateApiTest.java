package translatorTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateApiTest {
    @Test
    public void translateTest() throws FileNotFoundException, IOException {
        Translate translate = TranslateOptions
                .newBuilder()
                .setCredentials(
                    ServiceAccountCredentials
                                .fromStream(new FileInputStream(
                                        "C:/Users/sanghuncho/Documents/Google_Cloud/gkoo-translator-68c45ea2878f.json")))
                .build().getService();
        
        Translation translation =
                translate.translate(
                    "Beugen Sie rechtzeitig Falten rund um die Augenpartie vor und verwenden Sie die Eye Care von Martina Gebhardt. Das darin enthaltene Avocadoöl verleiht der Haut Elastizität und Spannkraft. Die Pflege erfrischt und belebt die müden Augen und zieht rasch in die Haut ein. zieht rasch ein mit Vitaminen und ätherischen Ölen erfrischend & belebend mehr Spannkraft & Elastizität Produktarten: Gesichtscremes Hauttyp: Reifere Haut Marken: Martina Gebhardt Eigenschaften: Plastikreduziert Zertifikate: IHTK Tierversuchsfrei, Demeter",
                    Translate.TranslateOption.sourceLanguage("de"),
                    Translate.TranslateOption.targetLanguage("ko"),
                    Translate.TranslateOption.model("nmt"));
        
        System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }
}
