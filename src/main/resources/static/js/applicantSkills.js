$(document).ready(function(){
	$("#addSkill").click(function(){
		let rowIndex = document.getElementById('skillsTable').getElementsByTagName('tr').length - 1;
		
		let createInput = function(id, rowIndex, fieldName) {
			let input = document.createElement('input');
			input.id = id;
			input.type = 'text';
			input.setAttribute('name', 'skillSet[' + rowIndex + '].' + fieldName);
			return input
		};
		
		let skillExp = "<td>" + createInput('skill', rowIndex, 'skill').outerHTML + "</td>" +
		"<td>" + createInput('yearsExp', rowIndex, 'yearsOfExperience').outerHTML + "</td>" +
		"<td><input id=\"removeSkill\" type=\"button\" name=\"removeRow\" value=\"Remove Skill\"></td>";
		
		let row = $("<tr></tr>").html(skillExp);
		$("#allSkills").append(row);
	});
});