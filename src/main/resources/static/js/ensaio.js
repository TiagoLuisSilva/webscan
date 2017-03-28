

var exibeEspessura  ;

var exibeLongitudinal ;

var exibeTransversal ;

var ensaioVar;
function iniciarTelaEnsaio(ensaio){
	ensaioVar = ensaio;
	exibeEspessura     = ensaio.espesura;
	exibeLongitudinal  = ensaio.longitudinal ;
	exibeTransversal   = ensaio.transversal;
	if (exibeEspessura){ 
		iniciarEspessura(ensaio.comprimentoMaximo); 
		if (ensaio.dadosEspessura !=null && ensaio.dadosEspessura.length >0){
			carregarLinhasEspessura(ensaio);
		}
	}
	if (exibeLongitudinal){ 
		iniciarLongitudinal(ensaio.comprimentoMaximo); 
		if (ensaio.dadosLongitudinal !=null && ensaio.dadosLongitudinal.length >0){
			carregarLinhasLongitudinal(ensaio);
		}
	}
	if (exibeTransversal){ 
	    iniciarTransversal(ensaio.comprimentoMaximo); 
		if (ensaio.dadosTransversal !=null && ensaio.dadosTransversal.length >0){
			carregarLinhasTransversal(ensaio);
		}
	}
	$(window).trigger('resize');

	$("#margemEspesura").val(ensaio.margemEspesura);
	$("#range").val(ensaio.margemEspesura);					
	$('#range').on("change mousemove", function() {
		var valor = $("#range").val();
		$("#margemEspesura").val(valor);
	}); 
	if (exibeEspessura && ensaio.margemEspesura != null){
		alterarLinha();
	}
	

	$("#margemLongitudinal").val(ensaio.margemLongitudinal);
	$("#rangeLongitudinal").val(ensaio.margemLongitudinal);					
	$('#rangeLongitudinal').on("change mousemove", function() {
		var valor = $("#rangeLongitudinal").val();
		$("#margemLongitudinal").val(valor);
	}); 
	if (exibeLongitudinal && ensaio.margemLongitudinal != null){
		alterarLinhaLongitudinal();
	}

	$("#margemTransversall").val(ensaio.margemTransversal);
	$("#rangeTransversal").val(ensaio.margemTransversal);					
	$('#rangeTransversal').on("change mousemove", function() {
		var valor = $("#rangeTransversal").val();
		$("#margemTransversal").val(valor);
	}); 
	if (exibeTransversal && ensaio.margemTransversal != null){
		alterarLinhaTransversal();
	}
	if (ensaio.id == null){
		$("#conectar").hide();
	}
	$("#desconectar").hide();

	$("#iniciar").hide();

	$("#parar").hide();
	
	$("#gravar").hide();

	if (ensaio.dadosEspessura !=null && ensaio.dadosEspessura.length >0){
		$("#conectar").hide(); 
	}
}


function editarEnsaio(idEnsaio){
	var host = window.location.origin;
	var destino =  host.concat('/webscan').concat('/dadosEnsaio/').concat(idEnsaio);
	return destino;
}


function conectar(){
	$("#desconectar").show();
	$("#conectar").hide();
	$("#iniciar").show();
}	
function desconectar(){
	$("#conectar").show();
	$("#desconectar").hide();  
	$("#iniciar").hide(); 
	$("#gravar").hide();
}
function iniciar(){
	$('#desconectar').attr("disabled", true);
	$("#parar").show();
	$("#iniciar").hide();
	$("#gravar").hide();
	if (exibeEspessura){ 
		iniciarEspessura(ensaioVar.comprimentoMaximo); 
		requestDataEspessura();
		alterarLinha();
	}
	if (exibeLongitudinal){ 
		iniciarLongitudinal(ensaioVar.comprimentoMaximo); 
		requestDataLongitudinal();
		alterarLinhaLongitudinal();
	}
	if (exibeTransversal){ 
	    iniciarTransversal(ensaioVar.comprimentoMaximo); 
		requestDataTransversal();
		alterarLinhaTransversal();
	}
}
function parar(){
	$('#desconectar').removeAttr( "disabled" );
	$("#iniciar").show();
	$("#parar").hide();
	$("#gravar").show();
	if (exibeEspessura){ 
	  pararLoopEspessura();
	}
	if (exibeLongitudinal){ 
	  pararLoopLongitudinal();
	}
	if (exibeTransversal){ 
	  pararLoopTransversal();
	}
	
}

function gravarDados(ensaio){
  
	if (exibeEspessura){
		ensaio = capturaLeituraEspessura(ensaio);
	}
	if (exibeLongitudinal){
		ensaio = capturaLeituraLongitudinal(ensaio);
	}
	if (exibeTransversal){
		ensaio = capturaLeituraTransversal(ensaio);
	}  
	
	ensaioLimpar = {id : ensaio.id, dadosEnsaio: null}
	objLimpar = JSON.stringify(ensaioLimpar);
	$.ajax({ 
		url: "/webscan/ensaio/limparDados",
        type: "POST",	  
        dataType: 'json', 
        contentType: 'application/json', 
        data: objLimpar
	 });
	dadosEnsaio = [];
	ensaio.dadosEnsaios.forEach(function(dado) {
	    dadoEnsaio = dado;
	    dadosEnsaio.push(dadoEnsaio);
	
	});
	ensaioGravar = {id : ensaio.id, dadosEnsaio: dadosEnsaio}; 
	obj = JSON.stringify(ensaioGravar);
	 $.ajax({ 
		   url: "/webscan/ensaio/gravar",
         type: "POST",	  
         dataType: 'json', 
         contentType: 'application/json', 
         data: obj
	 });
	
	
}

function enviarRelatorio(idEntidade){
 
	
	$.blockUI({ message: '<h1> Just a moment...</h1>' });
	var svgEspessura="";
	var svgLongitudinal="";
	var svgTransversal="";

	if (exibeEspessura){ 
		  svgEspessura  = getSvgEspessura(); 
	}
	if (exibeLongitudinal){ 
		  svgLongitudinal  = getSvgLongitudinal();
	}
	if (exibeTransversal){ 
		  svgTransversal  = getSvgTransversal();
	}

	var ensaioRelatorio = {id: idEntidade, svgEspessura: svgEspessura, svgLongitudinal  : svgLongitudinal , svgTransversal : svgTransversal  }
	

	obj = JSON.stringify(ensaioRelatorio);
	 $.ajax({ 
		   url: "/webscan/ensaio/download",
         type: "POST",	  
         dataType: 'json', 
         contentType: 'application/json', 
         data: obj,
         success: function(response){
        	 
         }
         
	 });  
	 sleep(3000);
	 window.open(
			  'http://localhost:9090/webscan/ensaio/download',
			  '_blank'  
			);
	 $.unblockUI();
}
function sleep(delay) {
    var start = new Date().getTime();
    while (new Date().getTime() < start + delay);
  }
