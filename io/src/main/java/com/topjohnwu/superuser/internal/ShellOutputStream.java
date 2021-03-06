/*
 * Copyright 2018 John "topjohnwu" Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.topjohnwu.superuser.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import androidx.annotation.NonNull;

class ShellOutputStream extends OutputStream {

    private ShellFileIO io;

    ShellOutputStream(ShellFile file, boolean append) throws FileNotFoundException {
        io = new ShellFileIO(file, append ? "rw" : "w");
    }

    @Override
    public void write(int b) throws IOException {
        byte[] buf = new byte[1];
        buf[0] = (byte) (b & 0xFF);
        write(buf);
    }

    @Override
    public void write(@NonNull byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(@NonNull byte[] b, int off, int len) throws IOException {
        io.append(b, off, len);
    }
}
