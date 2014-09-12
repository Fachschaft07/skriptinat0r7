<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>400 - Syntaktisch inkorrekter Request</h2>
        <p>Die URL, die Sie aufrufen, ist prinzipiell existent. Der Server versteht aber nicht, was Sie tun möchten. Versuchen Sie etwa Sicherheitslücken aufzudecken?</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
