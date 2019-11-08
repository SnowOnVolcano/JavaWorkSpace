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


# 创建窗口
root = Tk()
root.geometry('300x300')
# 创建并添加Canvas
canvas = Canvas(root, background='white', width = 1024, height = 1024)

delta_t = 1/300.0
ratio = 50
ratioHuman = 10
global r
r = 5

canvas.create_rectangle(9.308*ratio, 7.481*ratio, 10.478*ratio, 6.909*ratio, fill = 'black')
canvas.create_rectangle(3.18*ratio, 10.457*ratio, 3.66*ratio, 3.209*ratio, fill = 'black')
canvas.create_rectangle(3.18*ratio, 10.457*ratio, 12.44*ratio, 9.997*ratio, fill = 'black')
canvas.create_rectangle(11.96*ratio, 9.977*ratio, 12.44*ratio, 7.3375*ratio, fill = 'black')
canvas.create_rectangle(11.96*ratio, 6.3375*ratio, 12.44*ratio, 3.209*ratio, fill = 'black')
canvas.create_rectangle(3.18*ratio, 3.698*ratio, 12.44*ratio, 3.209*ratio, fill = 'black')

canvas.pack()

path = r'.\log.txt'  # java 生成log.txt 的路径
fm = np.loadtxt(path)
fm = fm.reshape(10400, 2)

oval_list = []
for i in range(26):
    oval_list.append(canvas.create_oval(start_x(fm[i*400][0] * ratio),start_y(fm[i*400][1] * ratio),
                       end_x(fm[i*400][0] * ratio),end_y(fm[i*400][1] * ratio), fill = 'red'))


for i in range(399):
    for j in range(26):
        if fm[i + 400*j+1][0] >= 12.44:
            canvas.delete(oval_list[j])
        else:
            canvas.move(oval_list[j], move_x(fm[i + 400*j+1][0] * ratio, fm[i + 400*j][0] * ratio),
                    move_y(fm[i + 400*j+1][1] * ratio, fm[i + 400*j][1] * ratio))
        root.update()
        time.sleep(delta_t)

root.mainloop()