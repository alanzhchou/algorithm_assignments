#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Author: Alan Chou 
# Date: 19/3/12
# version: 1.0
# python_version: 3.62

people_number = int(input())


sas = input().split(" ")
students = input().split(" ")

sas_prefer = []
students_prefer = []

for i in range(people_number):
   sas_prefer.append(input().split(" "))

for i in range(people_number):
    students_prefer.append(input().split(" "))

matched_pair = {}

'''
稳定匹配策略

基础
1. sa 选择最优先级的学生
2. 学生未匹配则接收匹配，否则判断该sa 与 已经匹配者的优先级

数据结构
0. 用int来进行主要部分的匹配运算
1. 储存 sa/student 名 -》 下标  =》 map
2. 储存 偏好列表 =》 二维数组
3. 储存未匹配的sa以用来快速寻找未匹配的 sa =》 map
'''









