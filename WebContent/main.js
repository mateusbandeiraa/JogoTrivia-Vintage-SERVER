ENDPOINT = "rest/";

function carregarElementos() {
	$.get("./card-ajudas.html", function (e) {
		cardAjudas = e;
	}, "html");
	$.get("./card-alternativa-aberta.html", function (e) {
		cardAlternativaAberta = e;
	}, "html");
	$.get("./card-alternativa-multipla-escolha.html", function (e) {
		cardAlternativaMultiplaEscolha = e;
	}, "html");
	$.get("./card-partida.html", function (e) {
		cardPartida = e;
	}, "html");
	cardQuestao = $.get("./card-questao.html", function (e) {
		cardQuestao = e;
	}, "html");

}
function matricularParticipante(nickname, idPartida, callback) {
	var dados = { idPartida: idPartida, nickname: nickname };

	$.ajax({
		url: ENDPOINT + "matricularParticipante/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			participante = result;
			callback(participante);
		}
	});
}

function iniciarPartida(idPartida, callback) {

	var dados = { idPartida: idPartida };

	$.ajax({
		url: ENDPOINT + "iniciarPartida/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			callback(result);
		}
	});
}

function criarPartida(idJogo, callback) {

	var dados = { idJogo: idJogo };

	$.ajax({
		url: ENDPOINT + "criarPartida/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			callback(result);
		}
	});

	// setInterval(atualizarQuestao, 1500);

}

function proximaQuestao(idPartida, callback) {
	var dados = { idPartida: idPartida };

	$.ajax({
		url: ENDPOINT + "proximaQuestao/",
		type: "POST",
		contentType: "application/json",
		data: dados,
		success: function (result) {
			callback(result);
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

function registrarInteracao(idPartida, idQuestao, idOpcao, idParticipante, chave, callback) {
	$.ajax({
		url: ENDPOINT + "registrarInteracao/",
		type: "POST",
		data: {
			idPartida:idPartida,
			idQuestao: idQuestao,
			idOpcao: idOpcao,
			idParticipante: idParticipante,
			chave: chave
		},
		contentType: "application/json",
		success: function (e) {
			this.participante = e;
			callback();
		}
	});
}

function criarJogoPadrao(callback){
	$.ajax({
		url: ENDPOINT + "criarJogoPadrao/",
		type: "POST",
		success: function (e) {
			callback(e);
		}
	});
}

function sincronizarHora(){
	$.ajax({
		url: ENDPOINT + "obterHora/",
		type: "GET",
		success: function (e) {
			diferencaHora = Date.now() - new Date(e);
		}
	});
}