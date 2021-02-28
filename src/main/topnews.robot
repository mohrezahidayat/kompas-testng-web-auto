*** Settings ***
Documentation               API test for top news
Library                     RequestsLibrary
Library                     JSONLibrary
Library                     OperatingSystem
Library                     XML

*** Variables ***
${base_url}                 https://rss.kompas.id/list
${endpoint}                 /top_news

*** Test Cases ***
Get 10 Data Top News
    Create Session          Get Top News            ${base_url}         verify=true
    ${results}=             Get Request             Get Top News        uri=${endpoint}
    ${string_results}=      Convert To String       ${results.content}
    ${data}=        Parse XML           ${string_results}
    ${count}=     Get Element Count        ${string_results}           .//channel/item
    Should Be Equal As Integers         ${count}            10

Get Return 200
    Create Session          Get Top News            ${base_url}         verify=true
    ${results}=             Get Request             Get Top News        uri=${endpoint}
    ${string_results}=      Convert To String       ${results.status_code}
    Should Be Equal As Integers         ${string_results}          200
