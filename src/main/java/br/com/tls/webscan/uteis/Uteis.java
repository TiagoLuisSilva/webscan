package br.com.tls.webscan.uteis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Uteis {

	public static Date getData0000(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		
		return calendar.getTime();
	}

	public static Date getData2359(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 58);
		calendar.set(Calendar.SECOND, 58);
		
		return calendar.getTime();
	}
	public static HttpEntity<byte[]>  abrirPdf(byte[] arquivo, String nome){
		

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "pdf"));
	    header.set("Content-Disposition",
	                   "attachment; filename=" + nome);
	    header.setContentLength(arquivo.length);

	    return new HttpEntity<byte[]>(arquivo, header);
		
	}

	@SuppressWarnings("deprecation")
	public static String converterSvgToJpg(String arquivo ) throws Exception {

        String svg_URI_input = new File(arquivo+".svg").toURL().toString();
        TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);        
        OutputStream jpg_ostream = new FileOutputStream(arquivo+".png");
        TranscoderOutput output_jpg_image = new TranscoderOutput(jpg_ostream);   
        PNGTranscoder my_converter = new PNGTranscoder();
         
        my_converter.transcode(input_svg_image, output_jpg_image); 
        jpg_ostream.flush();
        jpg_ostream.close();  
        
		return arquivo.subSequence(1, arquivo.length())+".png";
	}
	
	
	
}
