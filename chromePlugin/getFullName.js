function getFullName(element,text,abbreviation,fullname){
	if (text.indexOf(abbreviation) > -1){
		var tooltip = abbreviation + ": " + fullname;
		element.setAttribute('title',tooltip)
	}
}