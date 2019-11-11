from tkinter import *
import time
import numpy as np


def start_x(mid_x):
    return (mid_x - r)


def start_y(mid_y):
    return (mid_y + r)


def end_x(mid_x):
    return (mid_x + r)


def end_y(mid_y):
    return (mid_y - r)


def move_x(end_x, begin_x):
    return end_x - begin_x


def move_y(end_y, begin_y):
    return end_y - begin_y


HUMAN_SUM = 50  # 总人数
WALL_WAST_X = 3.66  # 西侧墙的横坐标
WALL_EAST_X = 11.96  # 东侧墙的横坐标
WALL_SOUTH_Y = 3.689  # 南侧墙的纵坐标
WALL_NORTH_Y = 9.977  # 北侧墙的纵坐标
DOOR_SOUTH_Y = 6.3375  # 门南侧的纵坐标
DOOR_NORTH_Y = 7.3375  # 门北侧的纵坐标
BARRIER_WEST_X = 9.308  # 西侧墙的横坐标
BARRIER_EAST_X = 10.478  # 东侧墙的横坐标
BARRIER_SOUTH_Y = 6.909  # 南侧墙的纵坐标
BARRIER_NORTH_Y = 7.481  # 北侧墙的纵坐标

# 创建窗口
root = Tk()
root.geometry('300x300')
# 创建并添加Canvas
canvas = Canvas(root, background='white', width=1024, height=1024)

delta_t = 1 / 300.0
ratio = 50
ratioHuman = 10

global r
r = 5

canvas.create_rectangle(BARRIER_WEST_X * ratio, BARRIER_NORTH_Y * ratio, BARRIER_EAST_X * ratio,
                        BARRIER_SOUTH_Y * ratio, fill='black')
canvas.create_rectangle(3.18 * ratio, 10.457 * ratio, WALL_WAST_X * ratio, 3.209 * ratio, fill='black')
canvas.create_rectangle(3.18 * ratio, 10.457 * ratio, 12.44 * ratio, 9.997 * ratio, fill='black')
canvas.create_rectangle(WALL_EAST_X * ratio, WALL_NORTH_Y * ratio, 12.44 * ratio, DOOR_NORTH_Y * ratio, fill='black')
canvas.create_rectangle(WALL_EAST_X * ratio, DOOR_SOUTH_Y * ratio, 12.44 * ratio, 3.209 * ratio, fill='black')
canvas.create_rectangle(3.18 * ratio, 3.698 * ratio, 12.44 * ratio, 3.209 * ratio, fill='black')

canvas.pack()

path = r'.\log.txt'  # java 生成log.txt 的路径
fm = np.loadtxt(path)
fm = fm.reshape(len(fm), 2)

oval_list = []
for i in range(HUMAN_SUM):
    oval_list.append(canvas.create_oval(start_x(fm[i * 400][0] * ratio), start_y(fm[i * 400][1] * ratio),
                                        end_x(fm[i * 400][0] * ratio), end_y(fm[i * 400][1] * ratio), fill='red'))

for i in range(399):
    for j in range(HUMAN_SUM):
        if fm[i + 400 * j + 1][0] >= 12.44:
            canvas.delete(oval_list[j])
        else:
            canvas.move(oval_list[j], move_x(fm[i + 400 * j + 1][0] * ratio, fm[i + 400 * j][0] * ratio),
                        move_y(fm[i + 400 * j + 1][1] * ratio, fm[i + 400 * j][1] * ratio))
        root.update()
        time.sleep(delta_t)

root.mainloop()
