package br.com.tls.webscan.uteis;


import java.io.File;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class RelatorioUtils {


    public static String realizarExportacaoPDF(ParametrosRelVO superRelVO, JasperPrint print) throws Exception {
        String nome = superRelVO.getNomeRelatorio(); 
        File pdfFile = new File(superRelVO.getDiretorioRel() + File.separator + nome);
//        File pdfFile = new File(System.getProperty("java.io.tmpdir") + File.separator + nome);
        if (pdfFile.exists()) {
            try {
                pdfFile.delete();
            } catch (Exception e) {
                throw new Exception("Erro ao exportar arquivo em PDF.");
            }
        }
        JasperExportManager.exportReportToPdfFile(print,
        		pdfFile.getPath());
 
        return pdfFile.getPath();
    }
}
