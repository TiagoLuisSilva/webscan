

function editarEnsaio(idEnsaio){
	var host = window.location.origin;
	var destino =  host.concat('/webscan').concat('/dadosEnsaio/').concat(idEnsaio);
	return destino;
}