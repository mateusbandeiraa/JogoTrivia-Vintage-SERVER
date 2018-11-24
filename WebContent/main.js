ENDPOINT = "rest/";
idPartida = 26;
questaoRequerAtualizacao = false;
questaoAtual = null;

function carregarElementos() {
	$.get("./card-ajudas.html", function (e) {
		cardAjudas = e;
	}, "html");
	$.get("./card-alternativa-aberta.html", function (e) {
		cardAlternativaAberta = e;
	}, "html");
	$.get("./card-alternativa-multipla-escolha", function (e) {
		cardAlternativaMultiplaEscolha
	}, "html");
	$.get("./card-partida.html", function (e) {
		cardPartida = e;
	}, "html");
	cardQuestao = $.get("./card-questao.html", function (e) {
		cardQuestao = e;
	}, "html");

}
function matricularParticipante() {
	var nickname = $("nickname").value;

	var dados = { idpartida: idPartida, nickname: nickname };

	$.ajax({
		url: ENDPOINT + "matricularParticipante/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			participante = result;
		}
	});
}

function iniciarPartida() {

	var dados = { idPartida: idPartida };

	$.ajax({
		url: ENDPOINT + "iniciarPartida/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			obterQuestaoAtual();
			exibirQuestaoAtual(questaoAtual);
		}
	});

	setInterval(atualizarQuestao, 1500);

}

function proximaQuestao() {
	var dados = { idPartida: idPartida };

	$.ajax({
		url: ENDPOINT + "proximaQuestao/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			obterQuestaoAtual();
			exibirQuestaoAtual(questaoAtual);
		}
	});
}

function obterQuestaoAtual() {
	$.ajax({
		url: ENDPOINT + "obterQuestaoAtual/" + idPartida,
		type: "GET",
		success: function (result) {
			if (result != questaoAtual)
				questaoRequerAtualizacao = true;
			questaoAtual = result;
			return questaoAtual;
		}
	});
}

function exibirQuestao(questao) {
	var saida = "<h2>" + questao.textoPergunta + "</h2>";
	var opcoes = questao.opcoes.opcoes;
	for (var i = 0; i < opcoes.length; i++) {
		console.log(opcoes[i]);
		saida += "<h4>" + opcoes[i].texto + "</h4>";
	}
	$("#questao").html(saida);
}

function atualizarQuestao() {
	console.log("Atualizando questão...");
	$.when(obterQuestaoAtual()).then(function (e) {
		if (questaoRequerAtualizacao) {
			exibirQuestao(questaoAtual);
			questaoRequerAtualizacao = false;
		}
	});
}

function obterPartidasDisponiveis(callback) {
	$.ajax({
		url: ENDPOINT + "obterPartidasDisponiveis/",
		type: "GET",
		success: function (e) {
			partidasDisponiveis = e;
			callback(partidasDisponiveis);
		}
	});
}