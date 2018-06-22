package com.learn.utils;


import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;

public class VideoExtension implements BeforeTestExecutionCallback,
        AfterTestExecutionCallback {

  /*  private IVideoRecorder recorder;

    //@Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        recorder = RecorderFactory.getRecorder(VideoRecorder.conf().getRecorderType());
        recorder.start();
    }

   // @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        File video = stopRecording(fileName);
    }
*/
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {

    }
}
