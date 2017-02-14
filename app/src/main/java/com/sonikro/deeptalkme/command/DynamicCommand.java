package com.sonikro.deeptalkme.command;

import com.sonikro.deeptalkme.activity.control.ActivityControl;
import com.sonikro.deeptalkme.framework.ActivityCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jonathan Nagayoshi on 13/07/2016.
 */
public class DynamicCommand extends ActivityCommand {
    private Object mCaller;
    private String mMethodName;
    private Method mMethod;
    private Class[] mParameterTypes;
    private Object[] mParameters;
    private Class<?> mCallerClass;

    private Object mRollbackCaller;
    private Object mSuccessCaller;
    private Method mRollbackMethod;
    private Method mSuccessMethod;

    public DynamicCommand(ActivityControl listener)
    {
        super(listener);
    }

    @Override
    public void execute() throws Exception {
        super.execute();
        mCallerClass = mCaller.getClass();
        setMethod(mCallerClass.getDeclaredMethod(getMethodName(),mParameterTypes));
        getMethod().invoke(getCaller(), mParameters);

    }
    public void setRollbackCallback(Object caller, String method)
    {
        mRollbackCaller = caller;
        Class <?> callerClass = mRollbackCaller.getClass();
        try {
            mRollbackMethod = callerClass.getDeclaredMethod(method,Exception.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setOnSuccessCallback(Object caller,String method)
    {
        mSuccessCaller = caller;
        Class <?> callerClass = mSuccessCaller.getClass();
        try {
            mSuccessMethod = callerClass.getDeclaredMethod(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void rollback(Exception exception) {
        try {
            if(mRollbackMethod != null)
            {
                mRollbackMethod.invoke(mRollbackCaller,exception);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess() {
        try {
            if(mSuccessMethod != null)
            {
                mSuccessMethod.invoke(mSuccessCaller);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getCaller() {
        return mCaller;
    }

    public void setCaller(Object mCaller) {
        this.mCaller = mCaller;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

    public String getMethodName() {
        return mMethodName;
    }

    public void setMethodName(String mMethodName) {
        this.mMethodName = mMethodName;;
    }
    
    public void setParameterTypes(Class... parameterTypes)
    {
        mParameterTypes = parameterTypes;
    }

    public void setParameters(Object... parameters)
    {
        mParameters = parameters;
    }

}
