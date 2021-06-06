<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="test" type="com.zalisove.db.entity.Test" %>
<%@attribute name="testUrl" %>
<%@attribute name="complexity" %>
<%@attribute name="mark" %>


<a class="text-decoration-none link-dark" href="${testUrl}">

    <div class="col m-1 rounded-top shadow"
         style="max-width: 250px;max-height: 225px; min-width: 250px;min-height: 200px">
        <c:if test="${mark.length() > 0}">
            <div class="border border-bottom-0 border-2 border-dark"
                 style="max-width: 250px;
                    max-height: 25px;
                    min-width: 250px;
                    min-height: 25px; ">
                <div class="text-center">
                       Mark: ${mark}%
                </div>
            </div>
        </c:if>
        <div class="
         <c:choose>
                  <c:when test="${test.complexityId == 1}">
                          bg-success
                   </c:when>
                     <c:when test="${test.complexityId == 2}">
                           bg-warning
                    </c:when>
                     <c:when test="${test.complexityId == 3}">
                           bg-danger
                    </c:when>
         </c:choose>

        text-capitalize border border-2 border-dark "
             style="max-width: 250px;max-height: 25px;min-width: 250px;min-height: 25px;">
            <div class="text-center">
                ${complexity}
            </div>
        </div>
        <div class="overflow-hidden border border-top-0 border-2 border-dark"
             style="max-width: 250px;max-height: 150px; min-width: 250px;min-height: 150px">
            ${test.name}
        </div>
        <div class="border border-top-0 border-2 border-dark"
             style="max-width: 250px;
                    max-height: 25px;
                    min-width: 250px;
                    min-height: 25px; ">
            <div class="text-center">
                <% long hours = test.getTime() / 3600;
                    long minutes = (test.getTime() % 3600) / 60;
                    long seconds = test.getTime() % 60;
                    out.print(String.format("%02d:%02d:%02d", hours, minutes, seconds));%>
            </div>
        </div>
    </div>
</a>