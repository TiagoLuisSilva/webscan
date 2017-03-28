
var chartTransversal = null;
var loopTransversal = null;
var comprimentoMaximo = null;
var counter = 0;
function iniciarTransversal(tamanho)  { 	
	comprimentoMaximo = tamanho;

	chartTransversal = new Highcharts.Chart({ 
        chart: {
            renderTo: 'transversal',
            zoomType: 'x',
            type: 'line' 
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
                text: 'Transversal'
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


function alterarLinhaTransversal(){
	var valor = $("#rangeTransversal").val();
	var plotBand = chartTransversal.yAxis[0].plotLinesAndBands[0];
	plotBand.options.value  = valor;
	plotBand.render();
}

function carregarLinhasTransversal(ensaio){ 
	
	ensaio.dadosTransversal.forEach(function(dado) { 
		if (dado.valorY !=null){
			
			chartTransversal.series[0].addPoint(dado.valorY, true, false);
		}
	});
}


function requestDataTransversal() {
 
    var series = chartTransversal.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(-100, 100); 
     chartTransversal.series[0].addPoint(ponto, true, shift);
     counter ++;
     if (counter == comprimentoMaximo){
    	 pararLoopTransversal();
     } else {
    	 loopTransversal = setTimeout(requestDataTransversal, 1000);   
     }
}
function pararLoopTransversal(){
	clearTimeout(loopTransversal);
}

function capturaLeituraTransversal(ensaio){ 
	var tamanho = chartTransversal.series[0].points.length;

	if (typeof ensaio.dadosEnsaios[0] == 'undefined') {
		ensaio.dadosEnsaios =[];
	}
	for(i=0; i<tamanho; i++){
		var dadosEnsaios = {valorX : i, valorY : chartTransversal.series[0].points[i].y, tipo: 'TRANSVERSAL' };
	 
		ensaio.dadosEnsaios.push(dadosEnsaios);
	} 
	return ensaio;
}
 
function getSvgTransversal(){
	return chartTransversal.getSVG();
}
