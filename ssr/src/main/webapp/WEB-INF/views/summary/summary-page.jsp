
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="../layouts/header.jsp"%>
<html>
<head>
    <title>summary-page</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>  <!-- Chart.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/moment"></script>   <!-- Moment.js 추가 (시간 스케일 지원) -->
    <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-moment"></script>  <!-- Chart.js 시간 어댑터 -->
    <style>
        .chart-container {
            width: 50%;
            float: left;  /* 차트를 두 개씩 나란히 배치 */
            padding: 20px; /* 차트 사이에 여백을 추가 */
        }
        .full-width-container {
            width: 100%;
            float: left;
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="container w-75 mt-5 mx-auto p-4 bg-white shadow-sm rounded">
    <h1 class="text-center mb-4">뉴스 수집 현황</h1>
    <!-- 버튼 추가 -->
    <div class="text-center mb-4">
        <button class="btn btn-primary" onclick="toggleTotalCountChart()">총 수량 차트 보기</button>
        <button class="btn btn-primary" onclick="toggleDailyTotalChart()">일일 총 수량 차트 보기</button>
        <button class="btn btn-primary" onclick="toggleCompanyTotalChart()">회사별 총 수량 차트 보기</button>
    </div>



    <!-- 차트를 2개씩 나란히 배치 -->
    <div class="chart-container">
        <canvas id="totalCountChart" width="400" height="200"></canvas>
    </div>
    <div class="chart-container">
        <canvas id="dailyTotalChart" width="400" height="200"></canvas>
    </div>
    <div class="full-width-container">
        <canvas id="companyTotalChart" width="800" height="200"></canvas>
    </div>

<%--    <!-- 마지막 2개는 전체 폭으로 -->--%>
<%--    <div class="full-width-container">--%>
<%--        <canvas id="dailyCompanyTotalChart" width="800" height="200"></canvas>--%>
<%--    </div>--%>

    <!-- 클리어 float: 각 행이 제대로 구분되도록 -->
    <div style="clear: both;"></div>
</div>

<script>
    var totalCountChart, dailyTotalChart, companyTotalChart;

    function toggleTotalCountChart() {
        var canvas = document.getElementById('totalCountChart');
        if (totalCountChart) {
            totalCountChart.destroy(); // 기존 차트 제거
            canvas.style.display = 'none'; // 캔버스 숨기기
            totalCountChart = null; // 차트 객체 초기화
        } else {
            var totalCountCtx = canvas.getContext('2d');
            totalCountChart = new Chart(totalCountCtx, {
                type: 'bar',
                data: {
                    labels: ['Total Count'],
                    datasets: [{
                        label: 'Total Count',
                        data: [${summary.totalCount}],
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
            canvas.style.display = 'block'; // 캔버스 보이기
        }
    }

    function toggleDailyTotalChart() {
        var canvas = document.getElementById('dailyTotalChart');
        if (dailyTotalChart) {
            dailyTotalChart.destroy(); // 기존 차트 제거
            canvas.style.display = 'none'; // 캔버스 숨기기
            dailyTotalChart = null; // 차트 객체 초기화
        } else {
            var dailyTotalCtx = canvas.getContext('2d');
            var dailyLabels = [];
            var dailyData = [];
            <c:forEach var="daily" items="${summary.dailyTotal}">
            dailyLabels.push('${daily.date}');
            dailyData.push(${daily.totalCount});
            </c:forEach>

            dailyTotalChart = new Chart(dailyTotalCtx, {
                type: 'line',
                data: {
                    labels: dailyLabels,
                    datasets: [{
                        label: 'Daily Total',
                        data: dailyData,
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
            canvas.style.display = 'block'; // 캔버스 보이기
        }
    }

    function toggleCompanyTotalChart() {
        var canvas = document.getElementById('companyTotalChart');
        if (companyTotalChart) {
            companyTotalChart.destroy(); // 기존 차트 제거
            canvas.style.display = 'none'; // 캔버스 숨기기
            companyTotalChart = null; // 차트 객체 초기화
        } else {
            var companyTotalCtx = canvas.getContext('2d');
            var companyLabels = [];
            var companyData = [];
            var companyColors = [];
            <c:forEach var="company" items="${summary.companyTotal}">
            companyLabels.push('${company.company}');
            companyData.push(${company.totalCount});
            companyColors.push(getRandomColor());
            </c:forEach>

            companyTotalChart = new Chart(companyTotalCtx, {
                type: 'pie',
                data: {
                    labels: companyLabels,
                    datasets: [{
                        label: 'Company Total',
                        data: companyData,
                        backgroundColor: companyColors,
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    var label = context.label || '';
                                    var value = context.raw || 0;
                                    var total = context.dataset.data.reduce((acc, cur) => acc + cur, 0);
                                    var percentage = (value / total * 100).toFixed(2);
                                    return label + ': ' + value + ' (' + percentage + '%)';
                                }
                            }
                        }
                    }
                }
            });
            canvas.style.display = 'block'; // 캔버스 보이기
        }
    }

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
</script>


</body>
</html>
