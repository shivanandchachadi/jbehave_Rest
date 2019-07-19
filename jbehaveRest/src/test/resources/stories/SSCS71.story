Narrative: In order to verify add Bins

Scenario: Adding 100 rows to the empty Bins
Meta:
@acceptance
@RestAPI
@automation
@id SSCS-71-SC01
@productName MMS
@moduleName SF
@automatedBy BH09098
Given a Storage Location room[uri] is defined,there are zero bins defined
When user passes [user_id],room_id and number of [bins] to add as 100
Then the system will insert 100 rows/bins into the BIN table
And pass a message back indicating that 100 bins/rows are defined
Examples:
|user_id|uri|bins|
|abc123 |D71   |100 | 

Scenario: Updating X rows to the existing Bins
Meta:
@acceptance
@DB Validation
@automation
@id SSCS-71-SC02
@productName MMS
@moduleName SF
@automatedBy BH09098
Given a Storage Location room[uri] is defined,there are bins 1 through 10 defined
When user passes [user_id], room_id and number of [bins] to add as x number
Then the system will insert X rows into the BIN table starting with Bin 11
And pass a message back indicating that 10+x number of bins are defined
Examples:
|uri|User_ID|bins|
|http:// |abc123 |20  |

Scenario: Verify that user adding less than 1 bin
Meta:
@acceptance
@DB Validation
@automation
@id SSCS-71-SC03
@productName MMS
@moduleName SF
@automatedBy BH09098
Given a Storage Location room [uri]information is defined
When user passes [user_id], room_id and number of [bins] to add as less than 1 bin
Then the system will not insert any rows into the BIN table
And pass a error message back saying that number of bins to add must be a number greater than zero.
Examples:
|uri|User_ID|bins|
|http://|abc123 |0   |

Scenario: Verify user-id is required to create bins
Meta:
@acceptance
@DB Validation
@automation
@id SSCS-71-SC04
@productName MMS
@moduleName SF
@automatedBy BH09098
Given a Storage Location room [uri]details is defined
When user doesn't pass a user_id but every other input data [bin]
Then the system will not insert any input data into the BIN table
And pass a error message back saying that user_id is required
Examples:
|uri|User_ID|bin|
|http://|abh123|20|

Scenario: Verifying for invalid room_id
Meta:
@acceptance
@RestAPI
@automation
@id SSCS-71-SC05
@productName MMS
@moduleName SF
@automatedBy BH09098

Given a user storage location[uri] room details
When user doesn't pass a valid room_id  but every other input data [bin],[userid]
Then the system will not add any rows into the BIN table
And pass a error message back saying that a valid room_id is required
Examples:
|uri|User_ID|bin|

|http://|abc123 |30| 

Scenario: Verify by adding more than 100 bins
Meta:
@acceptance
@RestAPI
@automation
@id SSCS-71-SC06
@productName MMS
@moduleName SF
@automatedBy BH09098

Given a Storage Location room[uri] is defined
When user passes [user_id], room_id and number of [bins] to add as more than allowed
Then the system will not insert rows into the BIN table
And pass an error message back saying bins to add can not be greater than 100 bins at a time.
Examples:
|uri|User_ID|bins|
|http://|abc123 |20|