
var chartLongitudinal = null;
var loopLongitudinal = null;
var comprimentoMaximo = null;
var counter = 0;
function iniciarLongitudinal(tamanho)  { 	
	comprimentoMaximo = tamanho;

	chartLongitudinal = new Highcharts.Chart({  
        chart: {
            renderTo: 'longitudinal',
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
        	 min: -100,

             max: 100,
            title: {
                text: 'Longitudinal'
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
            data: [0],
            lineWidth: 1
        } ]
    });
}

function alterarLinhaLongitudinal(){
	var valor = $("#rangeLongitudinal").val();
	var plotBand = chartLongitudinal.yAxis[0].plotLinesAndBands[0];
	plotBand.options.value  = valor;
	plotBand.render();
}

function carregarLinhasLongitudinal(ensaio){ 
	
	ensaio.dadosLongitudinal.forEach(function(dado) { 
		if (dado.valorY !=null){
			chartLongitudinal.series[0].addPoint(dado.valorY, true, false);
		}
	});
}


function requestDataLongitudinal() {
 
    var series = chartLongitudinal.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(-100, 100); 
     chartLongitudinal.series[0].addPoint(ponto, true, shift);
      
     counter ++;
     if (counter == comprimentoMaximo){
    	 pararLoopLongitudinal();
     } else {
         loopLongitudinal = setTimeout(requestDataLongitudinal, 1000);   
     }
}
function pararLoopLongitudinal(){
	clearTimeout(loopLongitudinal);
}


function capturaLeituraLongitudinal(ensaio){ 
	var tamanho = chartLongitudinal.series[0].points.length;

	if (typeof ensaio.dadosEnsaios[0] == 'undefined') {
		ensaio.dadosEnsaios =[];
	}
	for(i=0; i<tamanho; i++){
		var dadosEnsaios = {valorX : i, valorY : chartLongitudinal.series[0].points[i].y, tipo: 'LONGITUDINAL' };
	 
		ensaio.dadosEnsaios.push(dadosEnsaios);
	} 
	return ensaio;
}


function getSvgLongitudinal(){
	return chartLongitudinal.getSVG();
}