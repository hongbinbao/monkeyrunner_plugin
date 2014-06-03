from com.android.monkeyrunner import MonkeyRunner as runner
from com.android.monkeyrunner import MonkeyDevice as device
from com.bhb.verify import Verifier as vf

#image save path with file name
app_phone_screen_1 = '/home/test/Desktop/imonkeyrunner1/phone_dialer_screen_1.png'
#image checkpoint resource path with file name
app_phone_screen_1_checkpoint_1 = '/home/test/Desktop/imonkeyrunner1/phone_dialer_screen_1_checkpoint_1.png'

#image touch-able resource path with file name
app_phone_dialbutton_icon = '/home/test/Desktop/imonkeyrunner1/phone_dial_icon.png'

DUT = runner.waitForConnection()

DUT.wake()

DUT.press('KEYCODE_BACK', device.DOWN_AND_UP)

DUT.press('KEYCODE_BACK', device.DOWN_AND_UP)

DUT.press('KEYCODE_HOME', device.DOWN_AND_UP)

DUT.startActivity(action='android.intent.action.DIAL tel:10086')

runner.sleep(1)

DUT.takeSnapshot().writeToFile(app_phone_screen_1)
#find(string childPicturePath, string parentPicturePath, double similarity)
#similarity: the minimum similarity to use in a find operation. The value should be between 0 and 1
#searches the child picture using a default minimum similarity of 0.7 if not provide in method argument.
exist =  vf().find(app_phone_screen_1_checkpoint_1, app_phone_screen_1, 0.8)
if exist:
   pass
else:
   print '\n%s not found' % app_phone_screen_1_checkpoint_1

#touch the center point coordinate of the image which exists in current screenshot
DUT.takeSnapshot().writeToFile(app_phone_screen_1)
#search the touch-able image and return the center point location coordinate in natural screen orientation
#monkeyrunner will always take the top left corner coordinates of app's real orientation as it's (0.0) point. 
#similarity=0.8, rotation=90 (90,180,270 means real app screen orientation)
#0, natural display, app display orientation
#90, landscape display, rotating device 90 degrees counterclockwise
#180, natural invert display, rotating device 180 degrees counterclockwise
#270, landscape invert display, rotating device 180 degrees counterclockwise
point =  vf().getLocation(app_phone_dialbutton_icon, app_phone_screen_1, 0.8, 90)

if point:
    x_orientation_natural, y_orientation_natural = point
    #touch API of MonkeyDevice
    DUT.touch(x_orientation_natural, y_orientation_natural, device.DOWN_AND_UP)
else:
    print '\n%s not found' % app_phone_dialbutton_icon

