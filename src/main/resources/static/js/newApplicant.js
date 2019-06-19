$(document).ready(function(){
	$("#addSkill").click(function(){
		let skillExp = "<td><input id=\"skill\" type=\"text\" th:field=\"*{skills[__${skillStat.index}__].skill}\"></td>" +
				"<td><input id=\"yearsExp\" type=\"text\" th:field=\"*{skills[__${skillStat.index}__].yearsOfExperience}\"></td>";
		let row = $("<tr></tr>").html(skillExp);
		$("#skillset").append(row);
	});
});