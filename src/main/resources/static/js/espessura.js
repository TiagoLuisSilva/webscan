
var chartEspessura = null;
var loopEspessura = null;
var comprimentoMaximo = null;
var counter = 0;
function iniciarEspessura(tamanho)  { 	
	comprimentoMaximo = tamanho;
	chartEspessura = new Highcharts.Chart({
        chart: {
            renderTo: 'espessura',
            type: 'line',  
            zoomType: 'x'
        },
        title: {
            text: ' '
        },
        subtitle: {
            text: ' '
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
        xAxis: { 
        	min: 0,
        	max: tamanho
        },
        yAxis: {
        	 min: 0,
        	 minRange: 50,
             max: 100,
            title: {
                text: 'Espessura (%)'
            },
            plotLines: [{
                color: 'red', // Color value
                dashStyle: 'Solid', // Style of the plot line. Default to solid
                value: 0, // Value of where the line will appear
                width: 2 // Width of the line    
              }]
        }, 
        exporting: {
            buttons: {
            	contextButton: {
                    align: 'left',
                    x: 40,

                	menuItems: [{
                        textKey: 'downloadPNG',
                        onclick: function () {
                            this.exportChart();
                        }
                    }, {
                        textKey: 'downloadJPEG',
                        onclick: function () {
                            this.exportChart({
                                type: 'image/jpeg'
                            });
                        }
                    }]
                }	
            }
        },
        series: [{
            name: 'Valor',
            data: [100],
            lineWidth: 1
        } ]
    });
}

function alterarLinha(){
	var valor = $("#range").val();
	var plotBand = chartEspessura.yAxis[0].plotLinesAndBands[0];
	plotBand.options.value  = valor;
	plotBand.render();
}

function carregarLinhasEspessura(ensaio){ 
	
	ensaio.dadosEspessura.forEach(function(dado) { 
		if (dado.valorY !=null){
		     chartEspessura.series[0].addPoint(dado.valorY, true, false);
		}
	});
}

function resetDataEspessura(){ 
    var seriesLength = chartEspessura.series[0].points.length;
    for(var i = seriesLength -1; i > -1; i--) {
    	chartEspessura.series[0].points[0].remove(true,true); 
    }

    chartEspessura.series[0].addPoint(100, true, false);
    chartEspessura.xAxis[0].render(); 
}

function requestDataEspessura() {
 
    var series = chartEspessura.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(0, 100); 
     chartEspessura.series[0].addPoint(ponto, true, shift);

     counter ++;
     if (counter == comprimentoMaximo){
    	 pararLoopEspessura();
     } else {
         loopEspessura = setTimeout(requestDataEspessura, 1000);   
     }

}
function pararLoopEspessura(){
	clearTimeout(loopEspessura);
}


function capturaLeituraEspessura(ensaio){ 
	var tamanho = chartEspessura.series[0].points.length;
	if (ensaio.dadosEnsaios == null) {
		ensaio.dadosEnsaios =[];
	}
	for(i=0; i<tamanho; i++){
		var dadosEnsaios = {valorX : i, valorY : chartEspessura.series[0].points[i].y, tipo: 'ESPESSURA' };
	 
		ensaio.dadosEnsaios.push(dadosEnsaios);
	} 
	return ensaio;
}

function getSvgEspessura(){
	return chartEspessura.getSVG();
}