/****************************************************************************************
 * @author MarcosVP
 * @description Verifica se o string de uma data é válido
 * @param sData {string}
 */
function isDataValida(sData) {
	return (/^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/).test(sData);
}