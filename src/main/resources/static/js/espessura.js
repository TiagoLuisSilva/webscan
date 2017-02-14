
var chartEspessura = null;
var loopEspessura = null;
function iniciarEspessura()  { 	

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
        	max: 1000
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

function requestDataEspessura() {
 
    var series = chartEspessura.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(0, 100); 
     chartEspessura.series[0].addPoint(ponto, true, shift);
     

     loopEspessura = setTimeout(requestDataEspessura, 1000);   
}
function pararLoopEspessura(){
	clearTimeout(loopEspessura);
}