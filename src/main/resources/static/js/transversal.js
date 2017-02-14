
var chartTransversal = null;
var loopTransversal = null;
function iniciarTransversal()  { 	

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
         max: 1000 
        },
        yAxis: {
        	 min: -100,
             max: 100,
            title: {
                text: 'Transversal'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
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


function requestDataTransversal() {
 
    var series = chartTransversal.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(-100, 100); 
     chartTransversal.series[0].addPoint(ponto, true, shift);

     loopTransversal = setTimeout(requestDataTransversal, 1000);   
}
function pararLoopTransversal(){
	clearTimeout(loopTransversal);
}