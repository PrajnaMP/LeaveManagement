package com.example.prajna.leave;

import java.io.Serializable;

/**
 * Created by prajna on 6/9/16.
 */
public class LeaveClass implements Serializable {

    private String mDate, mName, mHoliday, mMobile, mLeaveType, mSickLeave, mCasualLeave, mEarnLeave, mReason, mDays, mFromDate, mToDate, mApplyTo;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getLeaveType() {
        return mLeaveType;
    }

    public void setLeaveType(String mLeaveType) {
        this.mLeaveType = mLeaveType;
    }

    public String getReason() {
        return mReason;
    }

    public void setReason(String mReason) {
        this.mReason = mReason;
    }

    public String getDays() {
        return mDays;
    }

    public void setDays(String mDays) {
        this.mDays = mDays;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String mFromDate) {
        this.mFromDate = mFromDate;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String mToDate) {
        this.mToDate = mToDate;
    }

    public String getApplyTo() {
        return mApplyTo;
    }

    public void setApplyTo(String mApplyTo) {
        this.mApplyTo = mApplyTo;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getSickLeave() {
        return mSickLeave;
    }

    public void setSickLeave(String mSickLeave) {
        this.mSickLeave = mSickLeave;
    }

    public String getCasualLeave() {
        return mCasualLeave;
    }

    public void setCasualLeave(String mCasualLeave) {
        this.mCasualLeave = mCasualLeave;
    }

    public String getEarnLeave() {
        return mEarnLeave;
    }

    public void setEarnLeave(String mEarnLeave) {
        this.mEarnLeave = mEarnLeave;
    }

    public String getHoliday() {
        return mHoliday;
    }

    public void setHoliday(String mHoliday) {
        this.mHoliday = mHoliday;
    }
}
