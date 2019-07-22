Sample story

Narrative:
As a store colleague, I need the ability to pull all bin id's assigned to a store in order to print  bar-codes used for put away.
					 
Meta:
@acceptance
@API_Testing
@automation
@id SSCS-135-SC01
@productName MMS
@moduleName SF
@automatedBy BH09098	
				 
Scenario: Verifying service when user passes div number and store number with room and bins defined
Given a Room is defined and the Room has bins Defined
When the User passes division[div_num] and store[store]
Then the service will return array of rooms defined with the number of bins

Examples:
|div_num|store_num|
|71|3|

Scenario: verify service when user passes roomid with defined room and bins
Given Room is defined and the Room has bins Defined
When the User passes room_id[room_id]
Then the service will return the array of bins defined for the room

Examples:
|room_id|
|D100212|

Meta:
@acceptance
@API_Testing
@automation
@id SSCS-135-SC02
@productName MMS
@moduleName SF
@automatedBy BH09098

Scenario: Verifying service when user passes only room id when room is defined without  bins.
Given a Room is defined and the Room has no bins defined
When the User passes room_id [room_id]
Then the service will return valid return code with room information but an empty bin array

Examples:
|room_id|
|D011000234|

Meta:
@acceptance
@API_Testing
@automation
@id SSCS-135-SC03
@productName MMS
@moduleName SF
@automatedBy BH09098

Scenario: Verifying service when user passes roomid along with start position and number of records when room is defined with many bins
Given a Room is defined and the room has many bins defined
When the User passes  room_id [room_id]  along with start position 0 [start_position], number of records 5[num_of_records]
Then service will return bin information for bin 1 through 5

Examples:
|room_id|start_postion|num_of_records|
|D011100012|0|5|

Meta:
@acceptance
@API_Testing
@automation
@id SSCS-135-SC04
@productName MMS
@moduleName SF
@automatedBy BH09098

Scenario: Verifying services when user passes division and store number with room undefined.
Given a Room is not defined
When User passes  division[div_num], store[store_num]
Then service will return good return code with  empty room array.

Examples:
|div_num|store_num|
|71|3|
