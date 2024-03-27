const today = new Date(), currentDate = today.getDate(), currentYear = today.getFullYear(), currentMonth = today.getMonth(), thanksgiving = (() => {
    const tempDate = new Date(currentYear, 10, 1);
    //4th Thursday of November
    tempDate.setDate(tempDate.getDate() - tempDate.getDay() + 25);
    return tempDate;
})(), data = [
    {
        label: 'Brochure Design Review',
        dateStart: new Date(currentYear, currentMonth, 10, 13, 15),
        dateEnd: new Date(currentYear, currentMonth, 12, 16, 15),
        status: 'tentative',
        class: 'event'
    }, {
        label: 'Website Re-Design Plan',
        dateStart: new Date(currentYear, currentMonth, 16, 16, 45),
        dateEnd: new Date(currentYear, currentMonth, 18, 11, 15),
        class: 'event'
    }, {
        label: 'Update Sales Strategy Documents',
        dateStart: new Date(currentYear, currentMonth, 2, 12, 0),
        dateEnd: new Date(currentYear, currentMonth, 2, 13, 45),
        class: 'event',
        repeat: {
            repeatFreq: 'daily',
            repeatInterval: 2,
            repeatEnd: 5,
            exceptions: [
                {
                    date: new Date(currentYear, currentMonth, 4, 12, 0),
                    label: 'Employee on sick leave. Reschedule for next day',
                    dateStart: new Date(currentYear, currentMonth, 5),
                    dateEnd: new Date(currentYear, currentMonth, 6),
                    status: 'outOfOffice',
                    backgroundColor: '#F06292'
                },
                {
                    date: new Date(currentYear, currentMonth, 8, 12, 0),
                    label: 'Employee on sick leave. Reschedule for next day',
                    dateStart: new Date(currentYear, currentMonth, 9),
                    dateEnd: new Date(currentYear, currentMonth, 10),
                    status: 'outOfOffice',
                    backgroundColor: '#FFA000'
                }
            ]
        }
    }, {
        label: 'Non-Compete Agreements',
        dateStart: new Date(currentYear, currentMonth, currentDate - 1, 8, 15),
        dateEnd: new Date(currentYear, currentMonth, currentDate - 1, 9, 0),
        status: 'outOfOffice',
        class: 'event'
    }, {
        label: 'Approve Hiring of John Jeffers',
        dateStart: new Date(currentYear, currentMonth, currentDate + 1, 10, 0),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 1, 11, 15),
        notifications: [
            {
                interval: 1,
                type: 'days',
                time: [today.getHours(), today.getMinutes()],
                message: 'Approve Hiring of John Jeffers tomorrow',
                iconType: 'success'
            }
        ],
        status: 'busy',
        class: 'event'
    }, {
        label: 'Update NDA Agreement',
        dateStart: new Date(currentYear, currentMonth, currentDate - 2, 11, 45),
        dateEnd: new Date(currentYear, currentMonth, currentDate - 2, 13, 45),
        class: 'event'
    }, {
        label: 'Update Employee Files with New NDA',
        dateStart: new Date(currentYear, currentMonth, currentDate + 2, 14, 0),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 2, 16, 45),
        class: 'event'
    }, {
        label: 'Compete Agreements',
        dateStart: new Date(currentYear, currentMonth, currentDate, today.getHours(), today.getMinutes() + 15),
        dateEnd: new Date(currentYear, currentMonth, currentDate, today.getHours() + 1, 45),
        notifications: [
            {
                interval: 0,
                type: 'days',
                time: [today.getHours(), today.getMinutes() + 1],
                message: 'Compete Agreements in 15 minutes',
                iconType: 'time'
            },
            {
                interval: 0,
                type: 'days',
                time: [today.getHours(), today.getMinutes() + 2],
                message: 'Compete Agreements in 14 minutes',
                iconType: 'warning'
            }
        ],
        status: 'outOfOffice',
        class: 'event'
    }, {
        label: 'Approve Hiring of Mark Waterberg',
        dateStart: new Date(currentYear, currentMonth, currentDate + 3, 10, 0),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 3, 11, 15),
        status: 'busy',
        class: 'event'
    }, {
        label: 'Update Employees Information',
        dateStart: new Date(currentYear, currentMonth, currentDate, 14, 0),
        dateEnd: new Date(currentYear, currentMonth, currentDate, 16, 45),
        class: 'event',
        repeat: {
            repeatFreq: 'weekly',
            repeatInterval: 2,
            repeatOn: [2, 4],
            repeatEnd: new Date(2021, 5, 24)
        }
    },
    {
        label: 'Prepare Shipping Cost Analysis Report',
        dateStart: new Date(currentYear, currentMonth, currentDate + 1, 12, 30),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 1, 13, 30),
        class: 'event',
        repeat: {
            repeatFreq: 'monthly',
            repeatInterval: 1,
            repeatOn: [new Date(currentYear, currentMonth, currentDate + 1)]
        }
    }, {
        label: 'Provide Feedback on Shippers',
        dateStart: new Date(currentYear, currentMonth, currentDate + 1, 14, 15),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 1, 16, 0),
        status: 'tentative',
        class: 'event'
    }, {
        label: 'Complete Shipper Selection Form',
        dateStart: new Date(currentYear, currentMonth, currentDate + 1, 8, 30),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 1, 10, 0),
        class: 'event'
    }, {
        label: 'Upgrade Server Hardware',
        dateStart: new Date(currentYear, currentMonth, currentDate + 1, 12, 0),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 1, 14, 15),
        class: 'event'
    }, {
        label: 'Upgrade Apps to Windows RT or stay with WinForms',
        dateStart: new Date(currentYear, currentMonth, currentDate + 2, today.getHours(), today.getMinutes() + 5),
        dateEnd: new Date(currentYear, currentMonth, currentDate + 2, today.getHours() + 2),
        status: 'tentative',
        class: 'event',
        repeat: {
            repeatFreq: 'daily',
            repeatInterval: 1,
            repeatOn: currentDate + 1,
            repeatEnd: new Date(currentYear, currentMonth, currentDate + 7),
            exceptions: [{
                    date: new Date(currentYear, currentMonth, currentDate + 4, 10, 30),
                    label: 'A day off work',
                    status: 'busy',
                    backgroundColor: '#64DD17'
                }]
        },
        notifications: [
            {
                interval: 2,
                type: 'days',
                time: [today.getHours(), today.getMinutes()],
                message: 'Upgrade Apps to Windows RT in 5 minutes',
                iconType: 'time'
            }
        ],
    },
    {
        label: 'Peter\'s Birthday',
        dateStart: new Date(currentYear, currentMonth, 5),
        dateEnd: new Date(currentYear, currentMonth, 6),
        class: 'birthday'
    },
    {
        label: 'Michael\'s Brithday',
        dateStart: new Date(currentYear, currentMonth, 10),
        dateEnd: new Date(currentYear, currentMonth, 11),
        class: 'birthday'
    },
    {
        label: 'Christina\'s Birthday',
        dateStart: new Date(currentYear, currentMonth, 20),
        dateEnd: new Date(currentYear, currentMonth, 21),
        class: 'birthday'
    }, {
        label: 'Halloween',
        dateStart: new Date(currentYear, 9, 31),
        dateEnd: new Date(currentYear, 9, 32),
        class: 'holiday'
    }, {
        label: 'Marry Christmas',
        dateStart: new Date(currentYear, 11, 24),
        dateEnd: new Date(currentYear, 11, 26, 23, 59, 59),
        class: 'holiday'
    },
    {
        label: 'Thanksgiving',
        dateStart: thanksgiving,
        dateEnd: new Date(currentYear, 10, thanksgiving.getDate() + 1),
        class: 'holiday'
    },
    {
        label: 'Day after Thanksgiving',
        dateStart: new Date(currentYear, 10, thanksgiving.getDate() + 1),
        dateEnd: new Date(currentYear, 10, thanksgiving.getDate() + 2),
        class: 'holiday'
    },
    {
        label: 'Indipendence Day',
        dateStart: new Date(currentYear, 6, 4),
        dateEnd: new Date(currentYear, 6, 5),
        class: 'holiday'
    },
    {
        label: 'New Year\'s Eve',
        dateStart: new Date(currentYear, 11, 31),
        dateEnd: new Date(currentYear + 1, 0, 1),
        class: 'holiday'
    }
];
window.Smart('#scheduler', class {
    get properties() {
        return {
            //Properties
            view: 'month',
            views: ['day',
                {
                    type: 'week',
                    hideWeekend: true,
                },
                {
                    type: 'month',
                    hideWeekend: true,
                }, 'agenda',
                {
                    label: '4 days',
                    value: 'workWeek',
                    type: 'week',
                    shortcutKey: 'X',
                    hideWeekend: false,
                    hideNonworkingWeekdays: true,
                }],
            nonworkingDays: getPastThreeWeekdays(today.getDay()),
            dateCurrent: new Date(),
            dataSource: data,
            firstDayOfWeek: 1,
            disableDateMenu: true,
            currentTimeIndicator: true
        };
    }
});
window.Smart('#calendar', class {
    get properties() {
        return {
            //Properties
            scrollButtonsPosition: 'far'
        };
    }
});
window.onload = function () {
    const primaryContainer = document.getElementById('primaryContainer'), calendar = document.getElementById('calendar'), scheduler = document.querySelector('smart-scheduler');
    document.getElementById('toggleButton').addEventListener('click', function () {
        primaryContainer.classList.toggle('collapse');
        scheduler.disableDateMenu = !primaryContainer.classList.contains('collapse');
    });
    document.getElementById('addNew').addEventListener('click', function () {
        scheduler.openWindow({ class: 'event' });
    });
    document.getElementById('calendar').addEventListener('change', function (event) {
        scheduler.dateCurrent = event.detail.value;
    });
    document.getElementById('tree').addEventListener('change', function (event) {
        const tree = event.target, types = tree.selectedIndexes.map((i) => tree.getItem(i).value);
        scheduler.dataSource = data.filter(d => types.indexOf(d.class) > -1);
    });
    scheduler.addEventListener('dragEnd', updateData);
    scheduler.addEventListener('resizeEnd', updateData);
    scheduler.addEventListener('itemUpdate', updateData);
    scheduler.addEventListener('itemRemove', updateData);
    //Update the Calendar date
    scheduler.addEventListener('dateChange', function (event) {
        calendar.selectedDates = [event.detail.value];
    });
};
function updateData(event) {
    const item = event.detail.item;
    for (let i = 0; i < data.length; i++) {
        const dataItem = data[i];
        if (dataItem.label === item.label && dataItem.class === item.class) {
            event.type === 'itemRemove' ? data.splice(i, 1) : data.splice(i, 1, item);
            return;
        }
    }
}
function getPastThreeWeekdays(weekday) {
    let weekdays = [];
    for (let i = 0; i < 3; i++) {
        weekdays.push((weekday - i + 7) % 7);
    }
    return weekdays;
}
export {};