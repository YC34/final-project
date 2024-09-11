<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../../layouts/header.jsp"%>
<html>
<head>
    <title>게시판 메인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<link rel="stylesheet" href="/css/board-page.css" type="text/css">
<body>
    <div class="container w-75 mt-5 mx-auto p-4 bg-white shadow-sm rounded">
        <h1 class="text-center mb-4">네이버 주식 뉴스</h1>
        <hr>
        <div class="chart-container">
            <div class="chart-box"><canvas id="myChart1"></canvas></div>
            <div class="chart-box"><canvas id="myChart2"></canvas></div>
            <div class="chart-box"><canvas id="myChart3"></canvas></div>
        </div>
        <br/>
        <br/>
        <table class="table table-hover table-striped text-center">
            <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">제목</th>
                <th scope="col">내용</th>
                <th scope="col">기사 날짜</th>
                <th scope="col">수집 날짜</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="news" items="${naverNewsList}" varStatus="status">
                <tr onclick="window.location.href='/board/detail?nid=${news.naverNewsSequence}'" style="cursor:pointer;">
                    <td>${(pageNum - 1) * 10 + status.index + 1}</td>
                    <td>${news.title}</td>
                    <td class="text-truncate" style="max-width: 150px;">${news.contents}</td>
                    <td>${news.newsDate}</td>
                    <td>${news.createAt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- 페이지 네비게이션 -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${pageNum > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?pageNum=${pageNum - 1}">이전</a>
                    </li>
                </c:if>

                <c:set var="startPage" value="${(pageNum - 1) / 10 * 10 + 1}"/>
                <c:set var="endPage" value="${startPage + 9}"/>
                <c:if test="${endPage > totalPage}">
                    <c:set var="endPage" value="${totalPage}"/>
                </c:if>

                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <li class="page-item ${i == pageNum ? 'active' : ''}">
                        <a class="page-link" href="?pageNum=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${pageNum < totalPage}">
                    <li class="page-item">
                        <a class="page-link" href="?pageNum=${pageNum + 1}">다음</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
    <script>
        const usdData = JSON.parse('${usdDataListJson}');
        const jpyData = JSON.parse('${jpyDataListJson}');
        const btcData = JSON.parse('${btcDataListJson}');

        function renderChart(id, data, labels,title,yMin,yMax) {

            const reversedLabels = labels.slice().reverse();


            const ctx = document.getElementById(id).getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: reversedLabels,
                    datasets: [{
                        label: '',
                        data: data,
                        borderWidth: 1
                    }]
                },
                options: {
                    plugins: {
                        title: {
                            display: true,
                            text: title
                        },
                        legend: {
                            display: false  // 범례 비활성화
                        },
                        tooltip: {
                            enabled: false  // 툴팁 비활성화
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: false, // Do not begin the y-axis at zero
                            min: yMin,         // Set the minimum value of the y-axis
                            max: yMax,         // Set the maximum value of the y-axis
                            ticks: {
                                stepSize: 10    // Optional: set step size for y-axis ticks
                            }
                        }
                    }
                }
            });
        }

        // Process and prepare the data
        function prepareData(data, text ) {
            // Extract labels and values from the data
            const labels = data.map(item => item.stockDate); // Assuming stockDate is the label
            const values = data.map(item => item.closeVol );  // Assuming closeVol is the value

            return { labels: labels, values: values, text: text };
        }

        const usdDataList = prepareData(usdData, '🇺🇸USD Data');
        const jpyDataList = prepareData(jpyData, '🇯🇵JPY Data');
        const btcDataList = prepareData(btcData, '𖡬BTC Data');

        const usdMinValue = Math.min(...usdDataList.values);
        const usdMaxValue = Math.max(...usdDataList.values);

        // Set the y-axis range
        const usdYMin = usdMinValue - 10;
        const usdYMax = usdMaxValue + 10;

        const jpyMinValue = Math.min(...jpyDataList.values);
        const jpyMaxValue = Math.max(...jpyDataList.values);

        // Set the y-axis range
        const jpyYMin = jpyMinValue  - 20;
        const jpyYMax = jpyMaxValue  + 20;

        const btcMinValue = Math.min(...btcDataList.values);
        const btcMaxValue = Math.max(...btcDataList.values);

        // Set the y-axis range
        const btcYMin = btcMinValue - 1000;
        const btcYMax = btcMaxValue + 1000;



        // Render charts with the data and text
        renderChart('myChart1', usdDataList.values, usdDataList.labels, usdDataList.text, usdYMin, usdYMax);
        renderChart('myChart2', jpyDataList.values, jpyDataList.labels, jpyDataList.text, jpyYMin, jpyYMax);
        renderChart('myChart3', btcDataList.values, btcDataList.labels, btcDataList.text, btcYMin, btcYMax);

    </script>
</body>
</html>