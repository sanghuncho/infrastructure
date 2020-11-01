package jasperReport;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportProducer {
    private Map<String, Object> parameter = new HashMap<>();

    //private List<KundenwechselOffUebersichtJahresabschlData> KundenwechselOffUebersichtJahresabschlDataList;
    private JRBeanCollectionDataSource dataSource = null;

    public void create(String title) {
        Objects.requireNonNull(title);
        //this.dataSource = new JRBeanCollectionDataSource(KundenwechselOffUebersichtJahresabschlDataList);
        parameter.put("title", title);
    }

    public Map<String, Object> getParameters() {
        return parameter;
    }

    public JRDataSource getDataSource() {
        return dataSource;
    }
}
